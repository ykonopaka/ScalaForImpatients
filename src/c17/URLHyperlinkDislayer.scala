package c17

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, _}
import scala.io.Source

/**
  * Created by Eugene on 7/3/2017.
  */
object URLHyperlinkDislayer extends App {

  val re = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

  def askUrl(): Future[String] = {
    Future(scala.io.StdIn.readLine())
  }

  def loadLinks(url: Future[String]): Future[List[String]] =
    url.map(u => re.findAllIn(Source.fromURL(u).mkString).matchData.map(_.group(2)).toList)

  def printLinks(links: Future[List[String]]): Future[Unit] =
    links.map(d => println(s"${d}\n"))

  val result = printLinks(loadLinks(askUrl()))

  Await.ready(result, Duration.Inf)

  println(result)
}
