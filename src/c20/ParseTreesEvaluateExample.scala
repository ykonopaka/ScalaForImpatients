package c20

import scala.util.parsing.combinator.RegexParsers

class ParseTreesEvaluateExample extends RegexParsers {
  val number = "[0-9]+".r

  def expr: Parser[Int] = (term ~ rep1(("+" | "-") ~ term)) ^^ {
    case a ~ List() => a
    case ex ~ op => {
      op.foldLeft(ex)((a, op) => if (op._1 == "+") {
        a + op._2
      } else {
        a - op._2
      })
    }
  }

  def term: Parser[Int] = (factor ~ opt("*" ~> term)) ^^ {
    case a ~ None => a
    case a ~ Some(b) => a * b
  }

  def factor: Parser[Int] = number ^^ (_.toInt) | "(" ~> expr <~ ")"
}

class Expr

case class Number(value: Int) extends Expr

case class Operator(op: String, left: Expr, right: Expr) extends Expr

object ParseTreesEvaluateExample extends App {
  val parseTrees = new ParseTreesEvaluateExample

  val parseTreesResult = parseTrees.parseAll(parseTrees.expr, "3-4-5")
  if (parseTreesResult.successful) println(parseTreesResult.get)
}