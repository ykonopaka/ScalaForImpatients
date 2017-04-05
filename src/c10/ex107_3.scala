package c10

/**
  * Created by Eugene on 4/5/2017.
  */
trait ConsoleLogger {
  val pre = "[LOG]"

  def log(line: String) = {
    println(pre + " " + line)
  }
}
