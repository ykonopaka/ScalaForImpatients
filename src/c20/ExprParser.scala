package c20

import scala.util.parsing.combinator.RegexParsers

class ExprParser extends RegexParsers {
  val number = "[0-9]+".r

  def expr: Parser[Int] = term ~ opt(("+" | "-") ~ expr) ^^ {
    case t ~ None => t
    case t ~ Some("+" ~ e) => t + e
    case t ~ Some("-" ~ e) => t - e
  }

  def term: Parser[Int] = power ~ rep(("*" | "/" | "%") ~ power) ^^ {
    case f ~ r => r.foldLeft(f)((g, op) => op._1 match {
      case "*" => g * op._2
      case "/" => g / op._2
      case "%" => g % op._2
    })
  }

  def power: Parser[Int] = rep(factor ~ "^") ~ factor ^^ {
    case r ~ f => r.foldRight(f)((m, p) => m._2 match {
      case "^" => math.pow(m._1, p).toInt
    })
  }

  def factor: Parser[Int] = number ^^ { _.toInt } | "(" ~ expr ~ ")" ^^ { case _ ~ e ~ _ => e }
}


