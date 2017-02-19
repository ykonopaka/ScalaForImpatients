import CardSuit.Value

import scala.math.BigDecimal
import scala.runtime.Nothing$
// 1
object Conversions  {
  def inchesToCentimeters(inches : Double) : Double = {
    inches * 2.54
  }

  def gallonsToLiters(gallons : Double) : Double = {
    gallons * 4.54609
  }

  def milesToKilometers(miles : Double) : Double = {
    miles * 1.609344
  }
}

assert(Conversions.inchesToCentimeters(10) == 25.4)
assert(Conversions.gallonsToLiters(10) == 45.4609)
assert(Conversions.milesToKilometers(10) == 16.09344)

// 2
abstract class UnitConversion() {
  def convert(input : BigDecimal) : BigDecimal
}

object InchesToCentimetersConversion extends UnitConversion {
  override def convert(input: BigDecimal): BigDecimal = {
    input * 4.54609
  }
}

object GallonsToLitersConversion extends UnitConversion {
  override def convert(input: BigDecimal): BigDecimal = {
    input * 4.54609
  }
}

object MilesToKilometersConversion extends UnitConversion {
  override def convert(input: BigDecimal): BigDecimal = {
    input * 1.609344
  }
}

object UnitConversion {
  def apply(from : String, to : String) : UnitConversion = {
    if (from.equals("gallon") && to.equals("liter"))  {
      InchesToCentimetersConversion
    }
    else if (from.equals("inch") && to.equals("cm"))  {
      GallonsToLitersConversion
    }
    else if (from.equals("mile") && to.equals("kilometer"))  {
      MilesToKilometersConversion
    } else {
      throw new RuntimeException(s"cannot convert from ${from} to ${to}")
    }
  }
}

assert(UnitConversion("mile", "kilometer").convert(100) == 160.934400)

// 3
object Origin extends java.awt.Point

// We have created singleton object which is mutable, isn't it?
Origin.setLocation(2.34D, 4.56D)

// 4
class Point(x : Double, y : Double)

object Point  {
  def apply(x: Double, y: Double): Point = new Point(x, y)
}

val a1 = Point(1D, 2D)

// 5
object HelloApp extends App {
  args.indices.reverse.foreach( i => println(args(i)))
}

// "c:\Program Files (x86)\scala\bin\scalac" HelloApp.scala
// "c:\Program Files (x86)\scala\bin\scala" HelloApp q w e r t y u
// u
// y
// t
// r
// e
// w
// q

// 6
object CardSuit extends Enumeration {
  val Hearts = Value("♥")
  val Spades = Value("♠")
  val Diamonds = Value("♦")
  val Clubs = Value("♣")
}

println(CardSuit.Clubs.toString)

// 7
def isRed(value : CardSuit.Value) : Boolean = {
  if (value == CardSuit.Hearts || value == CardSuit.Diamonds) true else false
}

assert(isRed(CardSuit.Hearts))

// 8
object RGBCube extends Enumeration {
  val Black = Value(0x000000)
  val Blue = Value(0x0000FF)
  val Red = Value(0xFF0000)
  val Green = Value(0x00FF00)
  val Purple = Value(0xFF00FF)
  val Yellow = Value(0xFFFF00)
  val Cyan = Value(0x00FFFF)
  val White = Value(0xFFFFFF)
}

