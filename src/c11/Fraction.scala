package c11

import scala.math.abs

/**
  * Created by Eugene on 4/15/2017.
  */
class Fraction(n: Int, d: Int) {
  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);

  override def toString = s"$num/$den"

  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def *(that: Fraction): Fraction = Fraction(num * that.num, den * that.den)

  def /(that: Fraction): Fraction = Fraction(num * that.den, den * that.num)

  def +(that: Fraction): Fraction = Fraction(num * that.den + that.num * den, den * that.den)

  def -(that: Fraction): Fraction = Fraction(num * that.den - that.num * den, den * that.den)
}

object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}


