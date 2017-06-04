import c14._
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

// 6
var tree: Node = Node(Node(Leaf(3), Leaf(8)), Node(Leaf(2), Node(Leaf(5), Node(Leaf(4), Leaf(5)))))

def nodeSum(input: BinaryTree): Int =
  input match {
    case Node(a, b) => nodeSum(a) + nodeSum(b)
    case Leaf(l) => l
  }

nodeSum(tree)

// 7
var treeExtended = ExtendedNode(ExtendedNode(ExtendedLeaf(3), ExtendedLeaf(4), ExtendedLeaf(8)), ExtendedNode(ExtendedLeaf(2), ExtendedNode(ExtendedLeaf(5), ExtendedNode(ExtendedLeaf(4), ExtendedLeaf(5)))))

def nodeSumExtended(input: ExtendedBinaryTree): Int =
  input match {
    case ExtendedNode(lst@_*) => lst.map(d => nodeSumExtended(d)).sum
    case ExtendedLeaf(l) => l
  }

nodeSumExtended(treeExtended)

// 8
var treeOp = OperationNode(Operation.+, OperationNode(Operation.*, OperationLeaf(3), OperationLeaf(4), OperationLeaf(8)), OperationNode(Operation.-, OperationLeaf(2), OperationNode(Operation.+, OperationLeaf(5), OperationNode(Operation.-, OperationLeaf(4), OperationLeaf(5)))))

def eval(input: OperationBinaryTree): Int = {
  input match {
    case OperationNode(o, lst@_*) => o match {
      case Operation.+ => lst.map(eval(_)).sum
      case Operation.- => -lst.map(eval(_)).sum
      case Operation.* => lst.map(eval(_)).product
      case Operation./ => 1 / lst.map(eval(_)).product
    }
    case OperationLeaf(l) => l
  }
}

eval(treeOp)

// 9
val opts: List[Option[Int]] = List(Option(0), Some(0), None, Some(3))

opts.flatten.sum

// 10
def compose(f1: Double => Option[Double],
            f2: Double => Option[Double]
           ): Double => Option[Double] =
  (x: Double) => f2(x) match {
    case Some(x) => f1(x)
    case None => None
  }


def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
def g(x: Double) = if (x >= 0) Some(math.sqrt(x)) else None

val h = compose(g, f)

h(2)
h(1)
h(0)
