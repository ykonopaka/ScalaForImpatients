import java.util.Calendar
import java.util.Calendar._

import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.mutable.{ArrayBuffer, _}
import scala.io.Source
import scala.util.Random

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
var r3 = Map[String, Int]()

for (w <- words)  {
  r3 += (w -> (1 + r3.getOrElse(w, 0)))
}

r3.toString()

// 4
var r4 = SortedMap[String, Int]()

for (w <- words)  {
  r4 += (w -> (1 + r4.getOrElse(w, 0)))
}

r4.toString()

// 5
var r5 : scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]()

for (w <- words)  {
  r5 += (w -> (1 + r5.getOrElse(w, 0)))
}

r5.toString()

// 6
var r6 = scala.collection.mutable.LinkedHashMap[String, Int]()

r6.put("Monday", MONDAY)
r6.put("Tuesday", TUESDAY)
r6.put("Wednesday", WEDNESDAY)
r6.put("Thursday", THURSDAY)
r6.put("Friday", FRIDAY)
r6.put("Saturday", SATURDAY)
r6.put("Sunday", SUNDAY)

for ((k, v) <- r6)  {
  println(f"${k} : ${v}")
}

// 7
var properties = java.lang.System.getProperties.toMap

val max = properties.keySet.seq.map(_.toString).map(_.length).max;

for ((k, v) <- properties)  {
  println(f"${k}${" " * (max - k.toString.length)} | ${v}")
}

// 8
def generateRandom(n: Int): Array[Int] =  {
  (for (e <- 0 until n) yield Random.nextInt(n)).toArray
}

def minmax(a: Array[Int]): Tuple2[Int, Int] = {
  val s = a.seq.sorted.toArray
  (s(0), s(a.length - 1))
}

val test: Array[Int] = generateRandom(5)

minmax(test)

// 9
def lteqgt(a: Array[Int], v: Int): Tuple3[Int, Int, Int] = {
  var lt = 0;
  var eq = 0;
  var gt = 0;

  for (e <- a)  {
    if (e < v) lt += 1
    if (e == v) eq += 1
    if (e > v) gt += 1
  }

  (lt, eq, gt)
}

val test9: Array[Int] = generateRandom(500)

lteqgt(test9, 10)

// 10
"Hello".zip(" World").toArray

