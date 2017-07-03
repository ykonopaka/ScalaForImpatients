package c17

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, _}

object PrimeNumberCalculator extends App {
  val cores = Runtime.getRuntime.availableProcessors

  def getIntervals(number: Int): List[Tuple2[Int, Int]] = {
    if (number < cores) {
      List(Tuple2(1, number))
    } else {
      val begins = (1 to (number * cores, number)).map(e => 1 + e / cores).toList
      val end = (for (f <- begins.tail) yield f - 1) :+ number

      begins.zip(end)
    }
  }

  def getFutures(intervals : List[Tuple2[Int, Int]]): Seq[Future[List[Int]]] = {
    for (d <- intervals) yield Future[List[Int]] {
      getPrimeNumbers(d._1, d._2)
    }
  }

  def getPrimeNumbers(interval: Tuple2[Int, Int]) : List[Int] = {
    val calcs = for (i <- interval._1 to interval._2) yield if (BigInt(i).isProbablePrime(100)) Some(i) else None
    calcs.filter(d => d.nonEmpty).map(f => f.get).toList
  }

  def doTogetherSeq(seq: Seq[Future[List[Int]]]): Future[List[Int]] = {
    val p = Promise[List[Int]]()
    p.success(List.empty[Int])

    val f: Future[List[Int]] = p.future

    seq.foldLeft(f) {
      (accumulator, future) => for {lst <- accumulator; a <- future} yield lst ++ a
    }
  }

  val f = doTogetherSeq(getFutures(getIntervals(100)))

  Await.ready(f, Duration.Inf)

  println(f)
}
