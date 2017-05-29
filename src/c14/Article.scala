package c14

/**
  * Created by Eugene on 5/29/2017.
  */
abstract class Item

case class Article(description: String, price: Double) extends Item

case class Bundle(description: String, discount: Double, items: Item*) extends Item

case class Multiple(number: Int, article: Article) extends Item
