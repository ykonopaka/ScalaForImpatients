import scala.math

object PrimeNumber extends App {
  val cores = Runtime.getRuntime.availableProcessors
  println(cores)

  def getIntervals(number: Int): List[Tuple2[Int, Int]] = {
    if (number < cores) {
      List(Tuple2(1, number))
    } else {
      var counters = 1 until(number, math.ceil(number.toDouble / cores))
      val intervals = for (i <- counters.indices) yield Tuple2(counters(i), counters(i + 1))
      (intervals :+ Tuple2(intervals.last._2, number)).toList
    }
  }

  println(getIntervals(7))
  println(getIntervals(10))
  println(getIntervals(20))
}
