import java.nio.file.{Path, Paths}

import c11.{PathComponents, _}
// 1
// val a = 3 + 4 -> 5
// val b = 3 -> 4 + 5
// As soon as both operators has the same priority they
// evaluate the left to right
// + and -> have the have priority because only first
// characters of each operation take to consideration
// + and - have the same presedence

// 2
val n = BigInt(10)
val p = 2
val r = n.pow(p)
// If there was an ** operation then * and ** would have the same
// priority and expression like
// 3 * 2 ** 4 would be 6 pow 4 instead of 3 * 2.pow(4)
// operation ^ has even lower presedence

// 3
val fr1 = Fraction(2, 3)
val fr2 = Fraction(1, 2)

val mul = fr1 * fr2
val div = fr1 / fr2
val add = fr1 + fr2
val sub = fr1 - fr2

// 4
// Division and multiplication are not applicable to Money
assert(Money(1, 75) + Money(0, 50) == Money(2, 25))
assert(Money(1, 75) - Money(0, 50) == Money(1, 25))

// 5
Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET" ||

// 6
val x = AsciiArt(
  """
 /\_/\
( ' ' )
(  -  )
 | | |
(__|__)
""")

val y = AsciiArt(
  """
   -----
 / Hello \
<  Scala |
 \ Coder /
   -----
""")

println(x * y + y * x)

//7
val bs = BitSequence(3L)
assert(bs.toString == "000000000000000000000000000000000000000000000000000000000000011")
assert(bs.packed == 3L)
bs(4) = 1
assert(bs(4) == 1)
assert(bs.toString == "000000000000000000000000000000000000000000000000000000000010011")
assert(bs.packed == 16L + 3L)
bs(5) = 1
assert(bs(5) == 1)
assert(bs.toString == "000000000000000000000000000000000000000000000000000000000110011")
assert(bs.packed == 32L + 16L + 3L)

// 8
val m1 = Matrix(Array(
  Array(2, 1),
  Array(-3, 0),
  Array(4, -1)
))

val m2 = Matrix(Array(
  Array(5, -1, 6),
  Array(-3, 0, 7)
))

val m3 = Matrix(Array(
  Array(3, 4),
  Array(4, 2),
  Array(1, -1)
))

// Addition
m1 + m3
// Multiplication
m1 * m2
// Multiplication by scalar
m1 * 2
// Access with indices
m1(0, 1)
// Update with indices
m2(1, 1) = 5

// 9
val path: Path = Paths.get("c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src\\c11\\", "chapter11.sc")
val pathComponents = PathComponents(path)

val PathComponents(parts) = pathComponents
assert(parts._1 == "c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src\\c11")
assert(parts._2 == "chapter11.sc")

// 10
val pathComponentsSeq = PathComponentsSeq(path)
val PathComponentsSeq(one, two, three, four, five, six, seven) = pathComponentsSeq

assert(one == "Users")
assert(two == "Eugene")
assert(three == "IdeaProjects")
assert(four == "ScalaForImpatients")
assert(five == "src")
assert(six == "c11")
assert(seven == "chapter11.sc")

// 11
val sysProps = new DynamicProps(System.getProperties)

val javaHome1 = sysProps.java.home
assert(javaHome1.toString == "D:\\Work\\Java\\jdk1.8.0_60\\jre")

sysProps.java.home = "/opt/java/jdk1.8.0_61/bin"

val javaHome2 = sysProps.java.home
assert(javaHome2.toString == "/opt/java/jdk1.8.0_61/bin")

// 12
val root: XMLElement = new XMLElement("root")
val attributes1 = List(("attr1", "val1"), ("attr2", "val2"), ("attr3", "val3"))
val attributes2 = List(("attr2", "val2"), ("attr3", "val3"))
val attributes3 = List(("attr5", "val5"))
val firstLevel1: XMLElement = new XMLElement("children1")
val firstLevel2: XMLElement = new XMLElement("children2", attributes1)
val firstLevel3: XMLElement = new XMLElement("children2", attributes2)
val firstLevel4: XMLElement = new XMLElement("children3", new XMLElement("children31"))
val firstLevel5: XMLElement = new XMLElement("children4", new XMLElement("children41", attributes3))
root.add(firstLevel1)
root.add(firstLevel2)
root.add(firstLevel3)
root.add(firstLevel4)
root.add(firstLevel5)

println(root.children2(attr1 = "val1"))
// Returns two:
// <children2 attr1='val1' attr2='val2' attr3='val3'></children2>
// <children2 attr2='val2' attr3='val3'></children2>

println(root.children3.children31)
// Returns
// <children31></children31>

println(root.children4.children41(attr5 = "val5"))
// Returns
// <children41 attr5='val5'></children41>

// 13
val builder = XMLBuilder()
builder.ul(id = "42", style = "list-style: lower-alpha;").tr.td(id = "43")
println(builder.build)
