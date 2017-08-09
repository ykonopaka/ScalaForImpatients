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
