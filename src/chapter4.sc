import java.io.File

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

// 1
val prices = Map[String, Double](
  "smartphone" -> 300.51,
  "tablet" -> 200.32,
  "charger" -> 32.65)
val discount = 0.1

for ((k, v) <- prices) yield (k, v * (1 - discount))

// 2
val source = Source.fromFile("D:\\words.txt", "UTF-8")
val lines = source.getLines().toArray
var words = ArrayBuffer[String]()

def strToWords(line: String): Array[String] =  {
  line.toLowerCase
    .replaceAll("[^\\w\\s]", "")
    .split("\\s+")
}

for (l <- lines)
  words.append(strToWords(l):_*)

var result = scala.collection.mutable.Map[String, Int]()

for (w <- words)  {
  result.put(w, 1 + result.getOrElse(w, 0))
}

result.toString()

// 3
var result3 = Map[String, Int]()

for (w <- words)  {
  result3 += (w -> (1 + result3.getOrElse(w, 0)))
}

result3.toString()

// 4
var result4 = scala.collection.mutable.SortedMap[String, Int]()

for (w <- words)  {
  result4 += (w -> (1 + result3.getOrElse(w, 0)))
}

result4.toString()