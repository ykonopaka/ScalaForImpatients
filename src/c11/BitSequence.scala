package c11

import java.lang

/**
  * Created by Eugene on 4/17/2017.
  */
class BitSequence(sequence: Long) {
  private var a = sequence

  def apply(number: Int): Int = {
    if (number >= 63) throw new IllegalArgumentException

    unpack()(number)
  }

  def update(number: Int, value: Int) = {
    if (number >= 63) throw new IllegalArgumentException
    if (value != 0 && value != 1) throw new IllegalArgumentException

    val r = unpack()
    r(number) = value

    a = pack(r)
  }

  private def unpack(): Array[Int] = {
    val r: Array[Int] = Array.fill[Int](63)(0)
    val v = a.toBinaryString.split("").reverse.map(s => s.toInt)

    for (i <- v.indices) r(i) = v(i)

    r
  }

  private def pack(raw: Array[Int]): Long = {
    lang.Long.parseLong(raw.mkString.reverse, 2)
  }

  override def toString: String = {
    unpack.mkString.reverse
  }

  def packed = a
}

object BitSequence {
  def apply(source: Long) = new BitSequence(source)
}


