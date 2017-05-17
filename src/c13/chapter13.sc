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



