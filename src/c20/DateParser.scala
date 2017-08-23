package c20

import java.time.LocalDateTime

import scala.util.parsing.combinator.RegexParsers

class DateParser extends RegexParsers {
  // yyyy-mm-ddThh:mm:ss.ffffff
  val value = "[0-9]+".r

  def number: Parser[Int] = value ^^ {
    _.toInt
  }

  def date: Parser[(Int, Int, Int)] = (number <~ "-") ~ (number <~ "-") ~ number ^^ {
    case year ~ month ~ day => (year, month, day)
  }

  def time: Parser[(Int, Int, Int, Int)] = (number <~ ":") ~ (number <~ ":") ~ number ~ opt("." ~> number) ^^ {
    case hours ~ minutes ~ seconds ~ None => (hours, minutes, seconds, 0)
    case hours ~ minutes ~ seconds ~ Some(millis) => (hours, minutes, seconds, millis)
  }

  def parseIso: Parser[LocalDateTime] = date ~ opt("T" ~> time) ^^ {
    case date ~ None => LocalDateTime.of(date._1, date._2, date._3, 0, 0)
    case date ~ Some(time) => LocalDateTime.of(date._1, date._2, date._3, time._1, time._2, time._3, time._4)
  }

}
