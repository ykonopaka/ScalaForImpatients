package c11

/**
  * Created by Eugene on 4/17/2017.
  */
class Table {
  var builder = new StringBuilder("<table><tr>")

  def |(column: String) = {
    builder.append(s"<td>${column}</td>")
    this
  }

  def ||(column: String) = {
    builder.append(s"</tr><tr><td>${column}</td>")
    this
  }

  def || = {
    builder.append(s"</tr></table>")
    this
  }

  override def toString: String = builder.toString()
}

object Table {
  def apply() = new Table()
}
