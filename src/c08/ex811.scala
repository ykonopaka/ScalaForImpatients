package c08

/**
  * Created by Eugene on 2/26/2017.
  */
class MyPoint(private val value : Long) extends AnyVal  {
  def x : Int = value.toInt;
  def y : Int = (value >> 32).toInt;

  override def toString: String = value.toString
}
