package c15

import scala.annotation.varargs

/**
  * Created by Eugene on 6/11/2017.
  */
class Summer {
  @varargs def sum(args: Int*): Int = args.sum
}
