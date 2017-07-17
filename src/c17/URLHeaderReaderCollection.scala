package c17

import java.net.URL
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.LongAdder

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.io.Source

/**
  * Created by Eugene on 7/17/2017.
  */
object URLHeaderReaderCollection extends App {
  val re = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

  val result = new ConcurrentHashMap[String, LongAdder]

  def loadLinks(): List[Future[String]] = {
    val url: String = scala.io.StdIn.readLine()

    re.findAllIn(Source.fromURL(url).mkString).matchData.map(_.group(2))
      .filter(s => s.startsWith("http")).map(str => Future {
      str
    }).toList
  }

  def processUrls(futures: List[Future[String]]): List[Future[String]] = {
    links.map(item => item.map(t => readHeader(t)))
  }

  def doTogetherSeq(seq: List[Future[String]]): Future[Unit] = {
    Future.sequence(seq).map(f => f.foreach(g => incrementAtomic(g)))
  }

  def readHeader(url: String): String = {
    new URL(url).openConnection.getHeaderField("Server")
  }

  def incrementAtomic(value: String): Unit = {
    result.computeIfAbsent(value, k => new LongAdder()).increment();
  }

  val links = loadLinks()
  val headers = processUrls(links)
  val done = doTogetherSeq(headers)

  Await.ready(done, Duration.Inf)

  println(result)
}
