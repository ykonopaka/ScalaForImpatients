import java.io.PrintWriter
import java.nio.file.Paths
import java.util.stream.DoubleStream

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

// Reading content into table
val raw = new ArrayBuffer[ArrayBuffer[String]]
for (l <- tabulated.getLines) raw.append(ArrayBuffer(l.split("\\t") : _*))

// We need to calculate lengths of each cell
val lengths = raw.map(d => d.map(r => r.length).toArray).toArray
val columns = lengths(0).length

// Calculate maximum length for each column
val max = lengths.toSeq.foldLeft(Array.fill[Int](columns)(0))(_.zip(_).map(c => math.max(c._1, c._2)))

// Add spaces
for (i <- raw.indices; j <- 0 until columns)
  raw(i)(j) = raw(i)(j) + f"${" " * (max(j) - raw(i)(j).length + 1)}"

// Form lines
val output = raw.seq.map(d => d.seq.foldLeft("")((a, b) => a + b)).toArray
val out = new PrintWriter(currDir.resolve("tabulated_new.txt").toFile)

// Output. I decided to output it to different file to see the difference
for (l <- output)
  out.println(l)

out.close()

// 3
Source.fromFile(currDir.resolve("words.txt").toFile, encoding)
  .getLines()
  .seq
  .map(s => s.split("\\s").toSeq)
  .flatten
  .map(s => s.replaceAll("[,.!?\\\\]", ""))
  .filter(d => d.length > 12)
  .toSet.foreach(println)

// 4
val stats = DoubleStream.of(
  Source.fromFile(
    currDir.resolve("floats.txt").toFile, encoding)
    .getLines
    .map(_.toDouble)
    .toArray : _*)
  .summaryStatistics()

val stats_out = new PrintWriter(currDir.resolve("floats_stats.txt").toFile)
stats_out.println(stats)
stats_out.close()

// 5
val lines_out = new PrintWriter(currDir.resolve("lines_out.txt").toFile)

(0 to 20)
  .map(s => Tuple2(s, math.pow(2D, -s)))
  .map(s => s"${s._1}\t | ${s._2}\n")
  .foreach(lines_out.println)

lines_out.close()