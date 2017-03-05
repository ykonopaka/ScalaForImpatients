// 1
def signum(number: Number): Number =  {
  if (number == 0) 0
  if (number.doubleValue() > 0) 1 else -1
}

signum(1)
signum(-1)
signum(0.000001)

// 2
{}

// 3
var un: Unit = {}
var nu: Double = 1.2D
un = nu = 2.3D

// 4
for (i <- Range.inclusive(10, 0, -1))
  println(i)

// 5
def countdown(int: Int) {
  for (i <- Range.inclusive(int, 0, -1))
    print(i)
}
countdown(13)

// 6
def unicode(str: String): Long = {
  var res: Long = 1
  for (a <- str)  {
    res = res * a.toInt
  }
  res
}

unicode("Hello")

// 7
def unicode2(str: String): Long = {
  if (str.length == 1) str.head.toInt
  else str.head.toInt * unicode2(str.tail)
}

unicode2("Hello")


