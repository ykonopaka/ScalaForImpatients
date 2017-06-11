package c15

import org.junit.Test

/**
  * Created by Eugene on 6/11/2017.
  */
class ex15_1 {

  @Test
  def test1(): Unit = {
    println("test 1")
  }

  @Test(timeout = 100L)
  def test2(): Unit = {
    println("test 2")
  }

  @Test(expected = classOf[RuntimeException])
  def test3(): Unit = {
    println("test 3")
    throw new RuntimeException
  }

  @Test(timeout = 10000L, expected = classOf[RuntimeException])
  def test4(): Unit = {
    println("test 4")
    throw new RuntimeException
  }
}
