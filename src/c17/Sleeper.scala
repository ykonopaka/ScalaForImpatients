package c17

import java.util.Date
import java.util.concurrent.Executors

import scala.concurrent.duration._
import scala.concurrent.{Future, _}

/**
  * Created by Eugene on 7/17/2017.
  */
object Sleeper extends App {
  val pool = Executors.newCachedThreadPool()
  implicit val ec = ExecutionContext.fromExecutor(pool)

  def getFuture: Future[Unit] = {
    Future {
      Thread.sleep(10000)
      println(s"Current time is ${new Date()}")
    }
  }

  val futures = (1 to 40).map(f => getFuture).toList

  val res: Future[List[Unit]] = Future.sequence(futures)

  Await.ready(res, Duration.Inf)

  println(res)
}
