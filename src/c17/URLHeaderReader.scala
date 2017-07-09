package c17

import java.net.URL

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.io.Source

/**
  * Created by Eugene on 7/3/2017.
  */
object URLHeaderReader extends App {
  val re = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

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

  def doTogetherSeq(seq: List[Future[String]]): Future[Map[String, Int]] = {
    Future.sequence(seq).map(lst => lst.groupBy(identity).mapValues(_.size))
  }

  def readHeader(url: String): String = {
    new URL(url).openConnection.getHeaderField("Server")
  }

  val links = loadLinks()
  val headers = processUrls(links)
  val collection = doTogetherSeq(headers)

  Await.ready(collection, Duration.Inf)

  println(collection)
}
