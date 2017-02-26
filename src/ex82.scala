/**
  * Created by Eugene on 2/26/2017.
  */
abstract class Figure {
  def area : Double
}

class Rectangle(val x: Double, val y: Double) extends Figure {
  override def area: Double = { x * y }
}

class Square(val s: Double) extends Rectangle(s: Double, s: Double) {}

class Circle(val r: Double) extends Figure {
  override def area: Double = math.Pi * r * r
}

class Triangle(a : Double, b : Double, c : Double) extends Figure {
  override def area: Double = {
    val p : Double = (a + b + c) / 2
    math.sqrt(p * (p - a) * (p - b) * (p - c))
  }
}
