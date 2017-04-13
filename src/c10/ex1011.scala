package c10

import java.io.{FileInputStream, InputStream}
import java.nio.file.Paths

/**
  * Created by Eugene on 4/5/2017.
  */
class IterableInputStream(stream: InputStream) extends InputStream with Iterable[Byte] {
  private val s = stream

  class IterableInputStreamIterator(inputStream: InputStream) extends Iterator[Byte] {
    override def hasNext: Boolean = {
      inputStream.available > 0
    }

    override def next(): Byte = {
      s.read().toByte
    }
  }

  override def iterator: Iterator[Byte] = {
    new IterableInputStreamIterator(s)
  }

  override def read(): Int = {
    s.read()
  }
}




