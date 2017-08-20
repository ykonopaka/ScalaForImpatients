package c19
import scala.language.reflectiveCalls

object PrintValues extends App {

  def printValues(f: {def apply(v: Int): Int}, from: Int, to: Int): Unit = {
    (from to to).foreach(d => println(f(d)))
  }

  printValues((x: Int) => x * x, 3, 6)
  printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)

}
