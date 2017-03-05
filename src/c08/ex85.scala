package c08

/**
  * Created by Eugene on 2/26/2017.
  */
class Point(val x : Double, val y : Double) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Point]

  override def equals(other: Any): Boolean = other match {
    case that: Point =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class LabeledPoint(val desc : String, x : Double, y : Double) extends  Point(x, y) {

  override def canEqual(other: Any): Boolean = other.isInstanceOf[LabeledPoint]

  override def equals(other: Any): Boolean = other match {
    case that: LabeledPoint =>
      super.equals(that) &&
        (that canEqual this) &&
        desc == that.desc
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(super.hashCode(), desc)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
