package c19



class Bug {
  private var position: Long = 0
  private var forward: Boolean = true

  def move(steps: Long): this.type = {
    if (forward) position += steps else position -= steps
    this
  }

  def show(): this.type = {
    println(position)
    this
  }

  def turn(): this.type = {
    forward = !forward
    this
  }

}

object Show
object Then
object Around


trait FluentBug { this: Bug =>
  def and(obj: Show.type): this.type = this.show()
  def and(obj: Then.type): this.type = this
  def turn(obj: Around.type): this.type = this.turn()
}




