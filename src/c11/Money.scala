package c11

/**
  * Created by Eugene on 4/16/2017.
  */
class Money(dollars: Int, cents: Int) {
  private val v = BigInt(dollars * 100 + cents)

  override def toString = s"$$${v / 100}.${v % 100}"

  def +(that: Money) = Money(v + that.v)

  def -(that: Money) = Money(v - that.v)

  def <(that: Money) = v < that.v

  def >(that: Money): Boolean = v > that.v

  def ==(that: Money) = v == that.v
}

object Money {
  def apply(dollars: Int, cents: Int) = new Money(dollars, cents)

  def apply(value: BigInt) = new Money(value.toInt / 100, value.toInt % 100)
}
