import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import java.awt.datatransfer._

// 1
def generateRandom(n: Int): Array[Int] =  {
  (for (e <- 0 until n) yield Random.nextInt(n)).toArray
}

generateRandom(10)
generateRandom(100)

// 2
def swap(a : Array[Int]): Unit = {
  for (e <- 0 until a.length / 2)  {
    val temp : Int = a(2 * e)
    a(2 * e) = a(2 * e + 1)
    a(2 * e + 1) = temp
  }
}

val test: Array[Int] = generateRandom(11)
swap(test)
val result = test

// 3
def swap2(a: Array[Int]): Array[Int] = {
  (for (i <- a.indices) yield
    a(
      if (i + 1 == a.length && a.length % 2 == 1) i
      else if (i % 2 == 0) i + 1
      else i - 1
    )).toArray
}

val test2: Array[Int] = generateRandom(10)
swap2(test2)

// 4
def generateRandom2(n: Int): Array[Int] =  {
  (for (e <- 0 until n) yield Random.nextInt()).toArray
}

val test3: Array[Int] = generateRandom2(11)

def resort(a: Array[Int]): Array[Int] = {
  val pos : Array[Int] = for (e <- a if e > 0) yield e
  val neg : Array[Int] = for (e <- a if e <= 0) yield e

  val r: ArrayBuffer[Int] = ArrayBuffer[Int]()
  r.append(pos : _*)
  r.append(neg : _*)

  r.toArray
}

resort(test3)

// 5
def generateRandom3(n: Int): Array[Double] =  {
  (for (e <- 0 until n) yield Random.nextDouble()).toArray
}

val s : Array[Double] = generateRandom3(30)
s.sum / s.length

// 6
def generateRandom4(n: Int): ArrayBuffer[Int] =  {
  val r: ArrayBuffer[Int] = ArrayBuffer[Int]()
  r.append((for (e <- 0 until n) yield Random.nextInt()).toArray : _*)
  r
}

val d : Array[Int] = generateRandom2(20)
val h : ArrayBuffer[Int] = generateRandom4(20)

for (f <- d.indices.reverse) yield d(f)
for (f <- h.indices.reverse) yield h(f)

// 7
val k : Array[Int] = Array(1,2,2,2,2,3,4,5)

val k2 : Array[Int] = k.distinct

// 8
def initial(a: ArrayBuffer[Int]) {
  var first = true
  var n = a.length
  var i = 0
  while (i < n) {
    if (a(i) >= 0)
      i += 1
    else {
      if (first) { first = false; i += 1  }
      else { a.remove(i); n -= 1 }
    }
  }
}

val t : ArrayBuffer[Int] = ArrayBuffer(3,4,5,-3,-2,5,7)
initial(t)
t.toArray

def solution(a: ArrayBuffer[Int]) {
  val neg : Array[Int] = (for (i <- a.indices if a(i) < 0) yield i).toArray
  val toRemove = neg.tail.reverse

  for (j <- toRemove) {
    a.remove(j)
  }
}

val t1 : ArrayBuffer[Int] = ArrayBuffer(3,4,5,-3,-2,5,7)
solution(t1)
t1.toArray

// 9
def solution2(a: ArrayBuffer[Int]) {
  val neg = (for (i <- a.indices if a(i) < 0) yield i).toArray.head
  val toKeep : Array[Int] = (for (i <- a.indices if a(i) > 0 || i == neg) yield i).toArray

  for (j <- toKeep.indices) a(j) = a(toKeep(j))

  a.trimEnd( a.length - toKeep.length)
}

val t2 : ArrayBuffer[Int] = ArrayBuffer(3,4,5,-3,-2,5,7)
solution2(t2)
t2.toArray

// 10
val americaPrefix = "America/"
(for (zone <- java.util.TimeZone.getAvailableIDs if zone.startsWith(americaPrefix)) yield zone.drop(americaPrefix.length)).sorted

// 11
val flavors = SystemFlavorMap
  .getDefaultFlavorMap()
  .asInstanceOf[SystemFlavorMap]
ArrayBuffer(flavors.getNativesForFlavor(DataFlavor.imageFlavor).toArray)