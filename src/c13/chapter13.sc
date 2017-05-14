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

