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