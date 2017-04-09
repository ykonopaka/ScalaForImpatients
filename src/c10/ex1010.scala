package c10

import java.io.{BufferedInputStream, InputStream}

/**
  * Created by Eugene on 4/9/2017.
  */
trait BufferedLike2 extends BufferedInputStream {
  this: InputStream with ConsoleLogger2 =>
  val size = 10240
  val buffered = new BufferedInputStream(this, size)

  override def read(ab: Array[Byte]): Int = {
    log("Reading with buffered")
    buffered.read(ab)
  }

  override def read(): Int = {
    log("Reading with buffered")
    buffered.read()
  }
}

trait Logger {
  def log(msg: String)
}

trait ConsoleLogger2 extends Logger {
  def log(msg: String) = println(msg)
}
