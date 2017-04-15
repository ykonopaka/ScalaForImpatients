import c11.Fraction
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

