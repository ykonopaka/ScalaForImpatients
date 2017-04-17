package c11

/**
  * Created by Eugene on 4/17/2017.
  */
class AsciiArt(source: String) {
  private val s = source

  def *(that: AsciiArt) = {
    AsciiArt(s.split("\n").toSeq
      .zip(that.s.split("\n"))
      .map(a => "%s\t%s\n".format(a._1, a._2))
      .foldLeft("")(_ + _))
  }

  def +(that: AsciiArt) = {
    AsciiArt("%s\n%s".format(s, that.s))
  }

  override def toString: String = s
}

object AsciiArt {
  def apply(source: String) = new AsciiArt(source)
}
