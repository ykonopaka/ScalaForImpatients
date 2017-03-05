package c08

/**
  * Created by Eugene on 2/26/2017.
  */
abstract class Shape {
  def centerPoint: Point
}

class Rect(val a: Point, val b: Point) extends Shape {
  override def centerPoint: Point = new Point((a.x + b.x) / 2, (a.y + b.y) / 2)
}

class Circ(val c: Point) extends Shape {
  override def centerPoint: Point = c
}
