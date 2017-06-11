package c15

/**
  * Created by Eugene on 6/11/2017.
  */
@deprecated
class AnnotatedClass @deprecated()(a: Int) {

  @deprecated
  val f: Double = 3.14

  @deprecated
  def method(@deprecated a: Int): Unit = {

    @deprecated
    val d = "String"
  }
}
