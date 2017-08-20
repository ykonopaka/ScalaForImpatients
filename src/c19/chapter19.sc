import c19.Network.NetworkMember
import c19._
import scala.language.reflectiveCalls

// 1
val bugsy = new Bug
bugsy.move(4).show().move(6).show().turn().move(5).show()

// 2
val show = Show
val then = Then
val around = Around

val fluentBugsy = new Bug with FluentBug
val fluentBugsy2 = new Bug with FluentBug

fluentBugsy.move(4).and(Show).and(Then).move(6).and(Show).turn(Around).move(5).and(Show)
fluentBugsy2 move 4 and show and then move 6 and show turn around move 5 and show

// 3
val book = new Document
book set Title to "Scala for the Impatient" set Author to "Cay Horstmann"

println(book)

// 4
val chatter = new Network
val myFace = new Network

val fred1 = chatter.join("Fred")
val fred2 = myFace.join("Fred")
val fred3 = chatter.join("Fred")
fred1.equals(fred2)
fred1.equals(fred3)

// 5
def process1(m1: NetworkMember, m2: NetworkMember): (NetworkMember, NetworkMember) = (m1, m2)

def process2[M <: n.Member forSome {val n : Network}](m1: M, m2: M): (M, M) = (m1, m2)

// This will work with members from two different networks
process1(new chatter.Member("m"), new myFace.Member("m"))
// This will not
process2(new chatter.Member("m"), new chatter.Member("m"))

//6
def closest(array: Array[Int], value: Int): Int Either Int = {
  var index: Int = 0
  var distance: Int = Int.MaxValue
  for (i <- array.indices) {
    var curr = Math.abs(array(i) - value)
    if (curr < distance) {
      distance = curr
      index = i
    }
  }
  if (array(index) == value) Left(index) else Right(index)
}

closest(Array(1, 2, 3, 4, 5, 7, 8, 9), 6)

// 7
def process[T <: {def close() : Unit}](target: T, func: T => Unit) {
  try {
    func(target)
  } finally {
    target.close()
  }
}

// 8
// PrintValues

// 9
// Dim

// 10
val self1 = new SelfType("one")
val self2 = new SelfType("one") with ValueTrait

