import scala.collection.GenSeq

// 1
def values(func: (Int) => Int, low: Int, high: Int): Array[(Int, Int)] = {
  (low to high).map(el => (el, func(el))).toArray
}

values(x => x * x, -5, 5)

// 2
val arr: Array[Int] = Array(5, 65, 34, 87, 123, 3, -3, 5, 100)

arr.seq.reduceLeft((a, b) => a max b)

// 3
def fact(n: Int): Long = {
  if (n < 1) 1
  else {
    (1 to n).map(_.toLong) reduceLeft (_ * _)
  }
}

fact(10)
fact(0)

// 4
def fact2(n: Int) = (1 to n).foldLeft(1)(_ * _)

fact2(10)
fact2(0)

// 5
def largest(fun: (Int) => Int, inputs: Seq[Int]): Int = {
  inputs.map(i => fun(i)).reduceLeft((a, b) => a max b)
}

largest(x => 10 * x - x * x, 1 to 10)

// 6
def largestAt(fun: (Int) => Int, inputs: Seq[Int]): Int = {
  inputs.map(i => (i, fun(i))).reduceLeft((a, b) => if (a._2 > b._2) a else b)._1
}

largestAt(x => 10 * x - x * x, 1 to 10)

// 7
def adjustToPair(fun: (Int, Int) => Int) = (x: (Int, Int)) => fun(x._1, x._2)

val x = adjustToPair(_ * _)((6, 7))

((1 to 10) zip (11 to 20)).map(a => adjustToPair(_ + _)(a))

// 8
val a = Array("Marry", "had", "a", "little", "lamb")
val b = Array(5, 3, 1, 6, 4)
val c = Array(5, 3, 1, 6, 6)

a.corresponds(b)((a, b) => a.length.equals(b))
a.corresponds(c)((a, b) => a.length.equals(b))

// 9
def myCorresponds[A, B](a: GenSeq[A], b: Array[B], compare: (A, B) => Boolean): Boolean = {
  a.zip(b).map(t => compare.apply(t._1, t._2)).reduceRight(_ && _)
}

myCorresponds(a, b, (a: String, b: Int) => a.length.equals(b))
myCorresponds(a, c, (a: String, b: Int) => a.length.equals(b))

// I encountered that I need to supply types in function which compares

// 10
trait Action {
  def otherwise(block: => Unit)
}

def unless(cond: Boolean)(block: => Unit) = {
  cond match {
    case false => {
      block
      new Action {
        override def otherwise(block: => Unit): Unit = {}
      }
    };
    case true => new Action {
      override def otherwise(block: => Unit): Unit = block
    }
  }
}

unless(3 > 5) {
  println("false")
} otherwise {
  println("true")
}

unless(5 > 3) {
  println("true")
} otherwise {
  println("false")
}

