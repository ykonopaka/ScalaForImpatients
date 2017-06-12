package c15

/**
  * Created by Eugene on 6/12/2017.
  */
class Factorial {
  def calculate(n: Int): Long = {
    assert(n > 0)
    (1 to n).foldLeft(1L)(_ * _)
  }

  calculate(-1)
}


object AppFactorial extends App {
  val f = new Factorial
  f.calculate(-1)
}
