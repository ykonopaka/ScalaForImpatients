package c10

trait LoggerExample {
  def log(msg: String)
}

trait ConsoleLoggerExample extends LoggerExample {
  def log(msg: String) {
    println(msg)
  }
}

trait TimestampLogger extends ConsoleLoggerExample {
  override def log(msg: String) {
    super.log(s" ${java.time.Instant.now()} $msg")
  }
}

trait ShortLogger extends ConsoleLoggerExample {
  override def log(msg: String) {
    super.log(if (msg.length <= 15) msg else s" ${msg.substring(0, 12)}...")
  }
}

class Entity

abstract class LoggedEntity extends Entity with LoggerExample {
  def action = {
    log("Action")
  }
}

class LoggedEntity1 extends Entity with TimestampLogger with ShortLogger
class LoggedEntity2 extends Entity with ShortLogger with TimestampLogger

object HelloApp extends App {
  val entity1 = new LoggedEntity with TimestampLogger with ShortLogger
  val entity2 = new LoggedEntity with ShortLogger with TimestampLogger

  entity1.log("text text text text text text text text text text text text")
  entity2.log("text text text text text text text text text text text text")
}







