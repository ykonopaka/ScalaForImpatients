package my

import scala.math.pow

package object random {
  private var seed : Int = 1
  private val a = 1664525
  private val b = 1013904223

  def nextInt : Int = {
    generate().intValue()
  }

  def nextDouble : Double = {
    generate().doubleValue()
  }

  def setSeed(seed: Int) : Unit = {
    this.seed = seed
  }

  def generate() : Number = {
    val next = (seed * a + b) % pow(2,32)
    setSeed(next.toInt)
    next
  }
}
