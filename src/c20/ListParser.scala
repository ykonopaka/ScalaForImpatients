package c20

import scala.collection.mutable.ArrayBuffer
import scala.util.parsing.combinator.RegexParsers

class ListParser extends RegexParsers {

  val number = "[0-9]+".r

  val list = new ArrayBuffer[Int]

  def element: Parser[Int] = number ^^ {
    _.toInt
  }

  def sequence: Parser[List[Int]] = (element ~ opt("," ~ element)) ^^ {
    case e ~ n => List.empty
    case e => List.empty
  }


}
