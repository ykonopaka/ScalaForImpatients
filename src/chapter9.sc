

import java.nio.file.Path
import java.nio.file.Paths

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

val currDir = Paths.get("c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src")
val encoding = "UTF-8"

// 1
val source = Source.fromFile(
  currDir.resolve("words.txt").toFile,
  encoding)

val r = new ArrayBuffer[String]
for (l <- source.getLines) r.append(l)

val result = r.seq.reverse.toArray

// 2
val tabulated = Source.fromFile(
  currDir.resolve("tabulated.txt").toFile,
  encoding)

val raw = new ArrayBuffer[ArrayBuffer[String]]
for (l <- tabulated.getLines) raw.append(ArrayBuffer(l.split("\\s") : _*))

val lengths = raw.map(d => d.map(r => r.length).toArray).toArray

