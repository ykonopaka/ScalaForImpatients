import scala.util.Random

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