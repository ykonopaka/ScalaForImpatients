import c07.Puzzle
// 1
// See ex71_1.scala and ex72_2.scala

// 2
// See ex72.scala

val p : Puzzle = new Puzzle
println(p.bzz.getClass)

// 3
import c07

random.nextDouble
random.nextInt

// 4
// It's not possible, JVM limitations

// 5
// Scala gives the possibility to extend the visibility of the variable
// into enclosing package
// If the package structure has at least two levels like
// com.level1 then it is useful because it extends the visibility to
// com package.
// however in real life there is no code in com that's why practically
// it is worthless

// 6 & 7
import java.util.{HashMap => JavaHashMap}
val javaMap = new JavaHashMap[String, String]

javaMap.put("one", "1")
javaMap.put("two", "2")

import scala.collection.mutable.{Map => ScalaMap}
val scalaMap = ScalaMap[String, String]()

Seq(javaMap.keySet)
  .flatMap(s => s.toArray())
  .map(_.toString)
  .foreach(k => scalaMap(k) = javaMap.get(k))

println(scalaMap)

// 8
// it is bad idea because there will be ambiguous package qualifiers

// 9
// See PasswordReader.scala

// 10
// java.lang.String, java.lang.Boolean