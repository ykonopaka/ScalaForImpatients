import scala.collection.mutable.ArrayBuffer

/**
  * Created by Eugene on 2/26/2017.
  */
abstract class Item {
  def price : Double
  def description : String
}

class SimpleItem(val price : Double, val description : String) extends Item {
  override def toString = s"SimpleItem($price, $description)"
}

class Bundle(items: Item*) extends Item {
  private val buffer : ArrayBuffer[Item] = ArrayBuffer(items: _*)

  override def price: Double = buffer.seq.foldLeft(0.0)(_ + _.price)

  override def description: String = buffer.mkString(start = "Items[", sep = ";", end = "]")

  def addItem(i : Item) = buffer.append(i)

  override def toString = s"Bundle($price, $description)"
}
