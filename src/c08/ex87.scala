package c08

/**
  * Created by Eugene on 2/26/2017.
  */
class Sq(x: Int, y: Int, width: Int) extends java.awt.Rectangle(x, y, width, 0) {

  def this(width: Int) {
    this(0, 0, width)
  }

  def this()  {
    this(0, 0, 0)
  }
}
