package c17

import java.net.URL

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}
import scala.io.Source

/**
  * Created by Eugene on 7/3/2017.
  */
object URLHeaderReader extends App {
  val re = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

  def askUrl(): Future[String] =
    Future(scala.io.StdIn.readLine())

  def loadLinks(url: Future[String]): Future[List[String]] =
    url.map(u => re.findAllIn(
      Source.fromURL(u).mkString).matchData.map(_.group(2)).filter(s => s.startsWith("http")).toList)

  def getServers(links: Future[List[String]]): Future[List[String]] = {
    links.map(lst => lst.map(l => readHeader(l)))
  }

  def reduce(res: Future[List[String]]): Future[Map[String, Int]] = {
    res.map(lst => lst.toSeq.groupBy(identity).mapValues(_.size))
  }

  def doTogetherSeq(seq: List[Future[String]]): Future[List[String]] = {
    val p = Promise[List[String]]()
    p.success(List.empty[String])

    val f: Future[List[String]] = p.future

    seq.foldLeft(f) {
      (accumulator, future) => for {lst <- accumulator; a <- future} yield lst ++ List(a)
    }
  }

  def readHeader(url: String): String = {
    new URL(url).openConnection.getHeaderField("Server")
  }

  val f = reduce(getServers(loadLinks(askUrl())))

  Await.ready(f, Duration.Inf)

  println(f)
}
