package c15

import scala.io.Source

/**
  * Created by Eugene on 6/11/2017.
  */
class FileReader {
  def read(path: String): String =
    Source.fromFile(path, "UTF-8").getLines().toArray.mkString(sep = "\r\n");
}
