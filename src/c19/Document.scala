package c19

object Title
object Author

class Document {
  private var title : String = _
  private var author : String = _
  private var useNextArgAs: Any = _

  def set(obj: Title.type): this.type = {
    useNextArgAs = obj; this
  }

  def set(obj: Author.type): this.type = {
    useNextArgAs = obj; this
  }

  def to(arg: String): this.type = {
    if (useNextArgAs == Title) title = arg; else author = arg;
    this
  }

  override def toString = s"Document($title, $author)"
}

