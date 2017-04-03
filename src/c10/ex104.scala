package c10

/**
  * Created by Eugene on 4/3/2017.
  */
trait CaesarCipherLogger {
  val key = 3;
  private val low = 97
  private val high = 122
  private val alphabet = (low to high).map(s => s.toChar).toArray

  def log(line: String) = {
    print(line.toLowerCase
      .toCharArray
      .toSeq
      .map(d => d.toInt)
      .map(f => if (low <= f && f <= high) ((f - low + 26 - key) % 26 + low) else f)
      .map(s => s.toChar).toArray
      .foldLeft("")((a, b) => a + b.toString))
  }
}

class DefaultCaesarCipherLogger extends CaesarCipherLogger;
