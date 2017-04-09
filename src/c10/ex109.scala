package c10

import java.io.{BufferedInputStream, InputStream}


/**
  * Created by Eugene on 4/9/2017.
  */
trait BufferedLike {

  this: InputStream =>
  val buffered = new BufferedInputStream(this)

  override def read(ab: Array[Byte]): Int = {
    buffered.read(ab)
  }
}
