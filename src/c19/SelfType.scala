package c19

class SelfType(val value: String) {
  override def toString: String = value
}

trait ValueTrait {
  self: SelfType =>

  override val value: String = "ValueTrait: " + self.value

  override def toString: String = "from Trait: " + super.toString
}
