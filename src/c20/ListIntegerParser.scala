package c20

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

class ListIntegerParser extends RegexParsers {

  val number: Regex = "-?[0-9]+".r

  def element: Parser[Int] = number ^^ { _.toInt }

  def sequence: Parser[List[Int]] = (element ~ rep("," ~ element)) ^^ {
    case e ~ n => e :: n.map(f => f._2)
    case e => List(e._1)
  }
}
