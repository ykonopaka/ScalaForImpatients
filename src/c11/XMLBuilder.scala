package c11

import scala.language.dynamics

class XMLBuilder extends Dynamic {
  private var root: XMLElement = null

  def selectDynamic(name: String): XMLBuilder = {
    if (root == null) {
      root = new XMLElement(name)
    } else {
      root.children.append(new XMLElement(name))
    }

    this
  }

  def applyDynamicNamed(name: String)(args: (String, String)*): XMLBuilder = {
    val attributes = args.seq.toList
    if (root == null) {
      root = new XMLElement(name, attributes)
    } else {
      root.children.append(new XMLElement(name, attributes))
    }
    this
  }

  def build: XMLElement = root

  def addChild(name: String): Unit = {
    root.children.append(new XMLElement(name))
  }
}

object XMLBuilder {
  def apply() = new XMLBuilder()
}

object Test extends App {
  val builder = XMLBuilder()
  builder.ul(id = "42", style = "list-style: lower-alpha;").tr.td(id = "43")

  println(builder.build)
}

