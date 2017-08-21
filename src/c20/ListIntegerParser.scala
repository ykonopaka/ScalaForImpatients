package c20

import scala.collection.mutable.ArrayBuffer
import scala.util.parsing.combinator.RegexParsers

class ListIntegerParser extends RegexParsers {

  val n = "-?[0-9]+".r

  val list = new ArrayBuffer[Int]

  def element: Parser[Int] = n ^^ {
    _.toInt
  }

  def sequence: Parser[List[Int]] = (element ~ rep("," ~ element)) ^^ {
    case e ~ n => e :: n.map(f => f._2)
    case e => List(e._1)
  }

}
