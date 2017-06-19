import scala.collection.mutable
import scala.xml.dtd.{DocType, PublicID}
import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.xml.{Attribute, Elem, Node}

// 1
val a = <fred>
  <greg>
    <rob></rob>
  </greg>
</fred> <dims>
  <rob></rob>
</dims>

a(0) //returns fred element
a(0)(0) //also returns fred element

// in first case a is NodeBuffer so a(0) return element #0, a(1) - element number 1
// in second case a(0) is Elem which NodeSeq with one element in it
// a(0)(0), a(0)(0)(0) will return the same element

// 2
/*
<ul>
  <li>Opening bracket: [</li>
  <li>Closing bracket: ]</li>
  <li>Opening brace: {</li>
  <li>Closing brace: }</li>
</ul>
*/

val fixed = <ul>
  <li>Opening bracket: [</li>
  <li>Closing bracket: ]</li>
  <li>Opening brace: {{</li>
  <li>Closing brace: }}</li>
</ul>

// 3

/*
<li>Fred</li> match {
  case <li>
    {Text(t)}
    </li> => t
}

<li>{Text("Fred")}</li> match {
  case <li>
    {Text(t)}
    </li> => t
}
*/

// Embedded strings do not get turned into Text nodes but into Atom[String] nodes.
// We need to rewrite second to use text or change matching to use Atom[String]

// 4
import scala.xml.{NodeBuffer, Text, XML}

val root = XML.loadFile("c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src\\c16\\page.xhtml")

(root \\ "img").filter(_.attribute("alt").isEmpty).foreach(println(_))

// 5
(root \\ "img" \\ "@src").foreach(println(_))

// 6
(root \\ "a").foreach(el => println(f"${el.attribute("href").getOrElse(Text(""))} : ${el.text}"))

// 7
def toXml(input: Map[String, String]): String = {
  val output = new NodeBuffer
  input.map(a => <dl>
    <dt>
      {a._1}
    </dt> <dd>
      {a._2}
    </dd>
  </dl>).foreach(output.append(_))
  output.mkString
}

toXml(Map("A" -> "1", "B" -> "2"))

// 8
def toMap(input: NodeBuffer): mutable.Map[String, String] = {
  val output: mutable.Map[String, String] = mutable.Map.empty
  input.map(node => node match {
    case <dl>
      {children@_*}
      </dl> => children
  }).foreach(s => output.put(s(0).text, s(1).text))
  output
}

toMap(<dl>
  <dt>A</dt> <dd>1</dd>
</dl> <dl>
  <dt>B</dt> <dd>2</dd>
</dl>)

// 9
val rule = new RewriteRule {
  override def transform(n: Node): Node = n match {
    case Elem(pr, "img", attr, sc, ns) if attr.get("alt").isEmpty =>
      Elem(pr, "img", attr, sc, true, ns) % Attribute(null, "alt", "TODO", null)
    case _ => n
  }
}

val transformed = new RuleTransformer(rule).transform(root)

// 10
XML.save("c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src\\c16\\myfile.xhtml",
  transformed.head,
  enc = "UTF-8",
  xmlDecl = true,
  doctype = DocType("html",
    PublicID("-//W3C//DTD XHTML 1.0 Strict//EN",
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"),
    Nil))
