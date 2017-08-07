package c17

import java.net.URL

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}
import scala.io.Source

/**
  * Created by Eugene on 7/18/2017.
  */
object URLHeaderPromises extends App {
  val re = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

  def getUrls(url: String): Future[List[String]] = {
    val urls = re.findAllIn(Source.fromURL(url).mkString).matchData.map(_.group(2))
      .filter(s => s.startsWith("http")).toList

    val promises = urls.map(u => {
      val p = Promise[String]()
      Future {
        val r = readHeader(u)
        p.success(r)
      }
      p
    })

    Future.sequence(promises.map(pr => pr.future))
  }

  def readHeader(url: String): String = {
    new URL(url).openConnection.getHeaderField("Server")
  }

  val results = getUrls("http://www.google.com")
  Await.ready(results, Duration.Inf)
  println(results)
}
