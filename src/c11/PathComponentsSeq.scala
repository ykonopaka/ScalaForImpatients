package c11

import java.nio.file.Path

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Eugene on 4/17/2017.
  */
class PathComponentsSeq(val path: Path)


object PathComponentsSeq {
  def apply(path: Path) = new PathComponentsSeq(path)

  def unapplySeq(pathComponentsSeq: PathComponentsSeq): Option[Seq[String]] = {
    val result = new ArrayBuffer[String]
    pathComponentsSeq.path.forEach(s => result.append(s.toString))
    println(result)
    Option(result)
  }
}


