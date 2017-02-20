import java.lang.System._

object PasswordReader extends App {
  val userName = getProperty("user.name")

  // Assume password is not longer than 100
  val input = new Array[Byte](100)
  out.println("Enter password for user " + userName + ": ")
  in.read(input)

  // Printable characters start from ASCII 20 and further
  val password =
    Seq(input.filter(_ > 20))
      .flatten
      .map(a => a.toChar.toString)
      .reduce((a, b) => a + b)

  if (password.equals("secret"))
    println("Come in " + userName)
  else
    println("No way")
}
