import c08._

// 1
val checkingAcc = new CheckingAccount(100);
checkingAcc.deposit(10)
checkingAcc.withdraw(5)
assert(checkingAcc.currentBalance == 103)

// 2
val savingsAcc: SavingsAccount = new SavingsAccount(100, 2);
savingsAcc.deposit(10)
savingsAcc.withdraw(5)
savingsAcc.earnMonthlyInterest
assert(savingsAcc.currentBalance == 105)

savingsAcc.withdraw(5)
savingsAcc.deposit(10)
savingsAcc.withdraw(10)
savingsAcc.earnMonthlyInterest
assert(savingsAcc.currentBalance == 98)

// 3
val rectangle : Figure = new Rectangle(3, 4);
val square : Figure = new Square(5);
val circle : Figure = new Circle(2);
val triangle : Figure = new Triangle(3, 4, 5);

assert(rectangle.area == 12)
assert(square.area == 25)
assert(math.abs(circle.area - 12.56637) < 0.01)
assert(triangle.area == 6)

// 4
val item1 : Item = new SimpleItem(1.01, "Apple")
val item2 : Item = new SimpleItem(2.01, "Banana")
val item3 : Bundle = new Bundle()
item3.addItem(item1)
item3.addItem(item2)
println(item3)

// 5
val point = new Point(2, 3)
val labeledPoint = new LabeledPoint("Black Thursday", 4, 5)

// 6
val rect = new Rect(new Point(0,0), new Point(2,4))
val circ = new Circ(new Point(5,5))

assert(rect.centerPoint == new Point(1,2))
assert(circ.centerPoint == new Point(5,5))

// 7
val sq1 = new Sq()
val sq2 = new Sq(3)
val sq3 = new Sq(5, 6, 7)

// 8
// private final name property exists in each class either in Person or in SecretAgent
// public method name exist in Person, in Secret Agent it overrides it
// See ex88.scala

// 9
val ant = new Ant
println(ant.env.length)

// When put val instead of def in '''def range: Int = 10''' in both
// parent and child class the issue disappears because there is no field which
// needs to be initialized
// However if to use def in child class the issue comes back because the
// field need to be initialized

// 10
// First protected keyword means protected primary constructor
// Protected in scala means that within package constructor is not accessible
// Second protected keyword means protected field List<A>
// The same - this field will be accessible only for children

// 11
val longValue = (3L << 32) + 5

val t : MyPoint = new MyPoint(longValue)
assert(t.x == 5)
assert(t.y == 3)