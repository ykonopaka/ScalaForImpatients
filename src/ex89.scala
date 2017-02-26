/**
  * Created by Eugene on 2/26/2017.
  */
class Creature {
  def range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature  {
  override def range = 2
}