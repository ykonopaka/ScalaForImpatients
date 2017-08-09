// 1
class PairImmutable[T, S](val f: T, val s: S) {
  def swap(): PairImmutable[S, T] = new PairImmutable(s, f)
}

// 2
class PairMutable[T](var f: T, var s: T) {
  def swap(): Unit = {
    val t = f
    this.f = s
    this.s = t
  }
}

// 3
def swap[T, S](par: PairImmutable[T, S]): PairImmutable[S, T] = {
  new PairImmutable(par.s, par.f)
}

// 4
// We don't need lower bound because it will be lowered to the very
// bottom of the hierarchy - to Any

// 5
// Comparable is java interface. So generic parameter should also be java
// Scala Int is translated into java Integer
// But RichInt can not be translated because there is no equivalent
// for RichInt is java

// 6
def middle[T](input: Iterable[T]) = input.toList(input.size / 2)
middle("World")

// 7
// Most of the methods produce values, that's why they are covariant in results

// 8
// Immutable:
class Pair[+T](val first: T, val second: T) {
  def replaceFirst[R >: T](newFirst: R) = new Pair[R](newFirst, second)
}

// Mutable:
class PairM[T](var first: T, var second: T) {
  def replaceFirst[R <: T](newFirst: R): Unit = {
    first = newFirst
  }
}

// When it is mutable it consumes, so contravariant;
// We can implement it but lower bound should be replaced with upper bound

// 9
/*
class PairD[+T](val first: T, val second: T) {
  def replaceFirst(newFirst: T) = new PairD(newFirst, second)
}

class NastyDoublePair(first: Double, second: Double) extends PairD[Double](first, second) {
  override def replaceFirst(newFirst: Double) = super.replaceFirst(math.sqrt(newFirst))
}

val p: PairD[Any] = new NastyDoublePair(4, 9)
p.replaceFirst("Hello")
*/

// 10
class PairS[S, T](var first: S, var second: T) {

  def swap(implicit ev: T =:= S): Unit = {
    val tmp = first.asInstanceOf[T]
    first = second
    second = tmp
  }
}

val one = new PairS(1, 2)
one.swap

val two = new PairS(1, "e")
//two.swap