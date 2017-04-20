package c11

import scala.collection.mutable.ListBuffer
import scala.language.dynamics

/**
  * Created by Eugene on 4/20/2017.
  */
class XMLElement(val name: String, val attributes: List[(String, String)], val children: ListBuffer[XMLElement]) extends Dynamic {

  def this(name: String) {
    this(name, List.empty, ListBuffer.empty)
  }

  def this(name: String, attributes: List[(String, String)]) {
    this(name, attributes, ListBuffer.empty)
  }

  def this(name: String, children: ListBuffer[XMLElement]) {
    this(name, List.empty, children)
  }

  def this(name: String, children: XMLElement) {
    this(name, List.empty, ListBuffer(children))
  }

  def add(element: XMLElement): Unit = {
    children.append(element)
  }

  override def toString: String = {
    val builder = StringBuilder.newBuilder
    builder.append("<")
    builder.append(s"${name}")
    attributes.seq.foreach(e => builder.append(s" ${e._1}='${e._2}'"))
    builder.append(">")
    children.seq.foreach(c => builder.append(c.toString))
    builder.append(s"</${name}>")
    builder.toString()
  }

  def selectDynamic(name: String): SearchHelper = {
    new SearchHelper(children.filter(e => e.name.eq(name)).toList)
  }

  def applyDynamicNamed(name: String)(args: (String, String)*): SearchHelper = {
    new SearchHelper(this).applyDynamicNamed(name)(args: _*)
  }
}

class SearchHelper(val result: List[XMLElement]) extends Dynamic {

  def this(element: XMLElement) {
    this(List(element))
  }

  def selectDynamic(name: String): SearchHelper = {
    new SearchHelper(result.seq.flatMap(el => el.children).filter(e => e.name.equals(name)).toList)
  }

  def applyDynamicNamed(name: String)(args: (String, String)*): SearchHelper = {
    val search = result.seq.flatMap(el => el.children).filter(e => e.name.equals(name))

    for ((k, v) <- args) {
      search.filter(e => e.attributes.contains(k, v))
    }

    new SearchHelper(search.toList)
  }

  override def toString: String = {
    val builder = StringBuilder.newBuilder
    result.seq.foreach(c => builder.append(c.toString + "\n"))
    builder.toString
  }
}
