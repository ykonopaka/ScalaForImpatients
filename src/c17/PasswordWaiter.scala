package c17

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, _}

/**
  * Created by Eugene on 7/2/2017.
  */
object PasswordWaiter extends App {
  def read(): String = {
    scala.io.StdIn.readLine()
  }

  def validate(passwd: String): Boolean = {
    Thread.sleep(1000)
    passwd.equals("secret")
  }

  def repeat[T](action: () => T, until: T => Boolean): Future[Boolean] = {
    val resultFuture = for (answer <- Future(action.apply()); result <- Future(until.apply(answer))) yield result

    Await.ready(resultFuture, Duration.Inf)

    if (!resultFuture.value.get.get) {
      repeat(action, until)
    }

    resultFuture
  }

  val d = repeat(read, validate)
}
