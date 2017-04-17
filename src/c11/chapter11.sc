import c11._
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
