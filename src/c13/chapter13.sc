import scala.Function.tupled
import scala.collection.mutable

// 1
def indexes(input: String): mutable.Map[Char, mutable.LinkedHashSet[Int]] = {
  val result = new mutable.HashMap[Char, mutable.LinkedHashSet[Int]]()

  input.zipWithIndex.foreach(a => {
    val current = result.getOrElse(a._1, new mutable.LinkedHashSet[Int]);
    current += a._2
    result(a._1) = current
  })

  result
}

indexes("Mississippi")

// 2
def indexes2(input: String): Map[Char, List[Int]] = input.zipWithIndex
  .groupBy(a => a._1)
  .toList
  .map(a => Tuple2(a._1.toChar, a._2.map(s => s._2).toList))
  .toMap

indexes2("Mississippi")

// 3
// Starting Test with 100001 elements
// Removing Test Time 439ms
// Creating New Test Time = 29ms

// 4
val array = Array("Tom", "Fred", "Harry")
val map = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)

array.flatMap(map.get).toList

// 5
var str = Array("Tom", "Fred", "Harry").mkString("b|", ",", "|e")

def myMkString(source: TraversableOnce[Any], begin: String, sep: String, end: String): String =
  begin + {
    if (source.isEmpty) "" else {
      source.reduceLeft((a, b) => a + sep + b)
    }
  } + end

myMkString(Array("Tom", "Fred", "Harry"), "b|", ",", "|e")
myMkString(Array(""), "b|", ",", "|e")
myMkString(Array.empty[String], "b|", ",", "|e")

// 6
val lst = List(1, 2, 3, 4)

(lst :\ List[Int]()) (_ :: _)
// is the same
lst.foldRight(List[Int]())(_ :: _)

(List[Int]() /: lst) (_ :+ _)
// is the same
lst.foldLeft(List[Int]())(_ :+ _)

lst.foldLeft(List[Int]())((a, b) => b :: a)

// 7
val prices = List(5.0, 20.0, 9.95)
val quantities = List(10, 2, 1)

prices zip quantities map {
  tupled(_ * _)
}

// 8
val arr = Array(1, 2, 3, 4, 5, 6)

def toTwoDimensions(n: Int, source: Array[Int]): Array[Array[Int]] = arr.grouped(n).toArray

toTwoDimensions(3, arr)

// 9
for (i <- 1 to 10; j <- 1 to i) yield i * j

(1 to 10).flatMap(i => (1 to i).map(j => i * j))
// it does this
// 1 produces array of 1
// 2 produces array of 2,4
// 3 produces array of 3,6,9
// Flatmap flattens everything into one sequence

for (i <- 1 to 10; j <- 1 to i; k <- 1 to 3) yield i * j * k
// The third loop appears inside

// 10
// (America,165),(Asia,93),(Europe,59),(Africa,54),(Pacific,43),(Etc,35),(Australia,23),(SystemV,13),(US,13),(Antarctica,12),(Atlantic,12),(Indian,11),(Canada,9),(Brazil,4),(Mexico,3),(Chile,2),(Arctic,1)
java.util.TimeZone.getAvailableIDs
  .map(_.split("/"))
  .filter(_.size > 1)
  .map(a => Tuple2(a(0), a(1)))
  .groupBy(d => d._1)
  .map(d => Tuple2(d._1, d._2.size))
  .toList
  .sortBy(s => s._2)
  .reverse
  .mkString(",")

// 11
// The solution is not thread safe
import scala.collection.immutable.Map

str.par.aggregate(Map[Char, Int]())(
  // Create a map or update if exists
  (map, count) => map + (count -> (map.getOrElse(count, 0) + 1)),
  // Merge to maps: Grouping by Chars and count sum
  (m1, m2) => (m1.toList ++ m2.toList).groupBy(s => s._1).map({ case (k, v) => (k, v.map(s => s._2).sum) })
)
