package c15

/**
  * Created by Eugene on 6/11/2017.
  */

object VolatileCheck extends App {

  class Volatile(@volatile var value: Boolean)

  var volatile: Volatile = new Volatile(false);

  val thread01: Runnable = () => {
    Thread.sleep(100L)
    volatile.value = true
    println("I'm the first thread and I'm done!")
  }

  val thread02: Runnable = () => {
    while (volatile.value.equals(false)) {
      println("I'm the second thread. Waiting for volatile to be true")
      Thread.sleep(0L, 1)
    }
    println("I'm the second thread and I'm done!")
  }
  new Thread(thread01).start()
  new Thread(thread02).start()
}
