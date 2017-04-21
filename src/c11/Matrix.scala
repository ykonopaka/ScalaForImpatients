package c11

/**
  * Created by Eugene on 4/17/2017.
  */
class Matrix(source: Array[Array[Int]]) {
  check(source)
  private val v = source
  private val m = v.length
  private val n = v(0).length

  override def toString: String = {
    v.toSeq.map(r => r.mkString("[\t", "\t", "\t]\n")).mkString(f"Matrix $m x $n\n", "", "\n")
  }

  private def check(source: Array[Array[Int]]) = {
    if (source.isEmpty) throw new IllegalArgumentException("Matrix should not be empty")
    source.map(s => s.length).toSet
  }

  def +(that: Matrix): Matrix = {
    if (m != that.m && n != that.n) throw new IllegalArgumentException("Dimensions of the matrices do not allow to add them")

    val result = Matrix(m, n)
    (0 until m).foreach(row => (0 until n).foreach(col => result(row, col) = this (row, col) + that(row, col)))

    result
  }

  def *(that: Matrix): Matrix = {
    if (n != that.m) throw new IllegalArgumentException("Dimensions of the matrices do not allow to multiply them")

    val result = Matrix(m, that.n)

    (0 until m).foreach(row => (0 until that.n).foreach(col => result(row, col) = multiply(this.row(row), that.col(col))))

    result
  }

  def *(that: Int): Matrix = {
    val result = Matrix(m, n)
    (0 until m).foreach(row => (0 until n).foreach(col => result(row, col) = this (row, col) * that))

    result
  }

  private def row(n: Int): Array[Int] = {
    v(n)
  }

  private def col(n: Int): Array[Int] = {
    v.map(s => s(n))
  }

  private def multiply(a: Array[Int], b: Array[Int]): Int = {
    a.zip(b).map(s => s._1 * s._2).sum
  }

  def apply(r: Int, c: Int) : Int = {
    if (r > m || c > n) throw new IllegalArgumentException("Out of matrix dimensions")
    v(r)(c)
  }

  def update(r: Int, c: Int, value: Int): Unit = {
    if (r > m || c > n) throw new IllegalArgumentException("Out of matrix dimensions")
    v(r)(c) = value
  }
}

object Matrix {
  def apply(source: Array[Array[Int]]) = new Matrix(source)

  def apply(m: Int, n: Int): Matrix = {
    new Matrix((1 to m).map(d => Array.fill[Int](n)(0)).toArray)
  }
}
