import c14.{Article, Bundle, Item, Multiple}
// 1
// Number of cases: 11059 hits in 680 files
// Number of fall-throughs 116 hits in 60 files

// 2
def swap(tuple: Tuple2[Int, Int]): Tuple2[Int, Int] = {
  tuple match {
    case (x, y) => (y, x)
  }
}

swap((3, 5))

// 3
def swap(array: Array[Int]): Array[Int] = {
  array match {
    case Array(x: Int, y: Int, rest@_*) => Array(y) ++ Array(x) ++ rest
    case _ => array
  }
}

swap(Array(1))
swap(Array(1, 2))
swap(Array(1, 2, 3))

// 4
def price(it: Item): Double =
  it match {
    case Article(_, p) => p
    case Bundle(_, disc, its@_*) => its.map(price _).sum - disc
    case Multiple(n, Article(_, p)) => p * n
  }

price(Multiple(10, Article("Blackwell Toaster", 29.95)))

// 5
var list: List[Any] = List(List(3, 8), 2, List(5))

def leafSum(input: List[Any]): Int =
  input.map(el => el match {
    case a: Int => a
    case b: List[Int] => leafSum(b)
    case _ => 0
  }).sum

leafSum(list)
