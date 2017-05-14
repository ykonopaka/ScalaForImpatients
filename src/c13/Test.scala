package c13

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Eugene on 5/14/2017.
  */
object Test extends App {

  def generate(): ArrayBuffer[Int] = {
    var size = 100000

    val buff = new ArrayBuffer[Int](size)

    (0 to size).foreach(a => buff.append(a))
    buff
  }

  var testRemove = generate()
  var testNew = generate()

  println(s"Starting Test with ${testRemove.length} elements")

  var testRemoveIn = System.currentTimeMillis()
  testRemove
    .reverse
    .filter(a => a % 2 == 0)
    .foreach(a => testRemove.remove(a))

  var testRemoveOut = System.currentTimeMillis()
  println(s"Removing Test Time ${testRemoveOut - testRemoveIn}ms")

  var testNewIn = System.currentTimeMillis()
  val copyAB = new ArrayBuffer[Int](testRemove.length / 2 + 1)
  testRemove
    .filter(a => a % 2 == 0)
    .foreach(a => copyAB.append(a))

  var testNewOut = System.currentTimeMillis()
  println(s"Creating New Test Time = ${testNewOut - testNewIn}ms")
}

