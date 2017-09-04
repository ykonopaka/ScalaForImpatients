package c20

import scala.util.parsing.combinator.RegexParsers

class ParseTreesExample extends RegexParsers {
  val number = "[0-9]+".r

  def expr: Parser[Expr] = (term ~ rep1(("+" | "-") ~ term)) ^^ {
    case a ~ List() => a
    case ex ~ a => a.foldLeft(ex)((k, l) => Operator(l._1, k, l._2))
  }

  def term: Parser[Expr] = (factor ~ opt("*" ~> term)) ^^ {
    case a ~ None => a
    case a ~ Some(b) => Operator("*", a, b)
  }

  def factor: Parser[Expr] = number ^^ (n => Number(n.toInt)) | "(" ~> expr <~ ")"
}

class Expr

case class Number(value: Int) extends Expr

case class Operator(op: String, left: Expr, right: Expr) extends Expr

object ParseTreesExample extends App {
  val parseTrees = new ParseTreesExample

  val parseTreesResult = parseTrees.parseAll(parseTrees.expr, "3-4-5")
  if (parseTreesResult.successful) println(parseTreesResult.get)
}