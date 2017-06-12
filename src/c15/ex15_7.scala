package c15

import scala.annotation.tailrec

/**
  * Created by Eugene on 6/12/2017.
  */
final class OverridenParent {
  @tailrec def sum(xs: Seq[Int], partial: BigInt): BigInt =
    if (xs.isEmpty) partial else sum(xs.tail, xs.head + partial)
}

object Application extends App {
  val p: OverridenParent = new OverridenParent
  println(p.sum(1 to 1000000, 0))
}
