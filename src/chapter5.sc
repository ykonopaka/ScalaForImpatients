import scala.beans.BeanProperty
// 1
/*class Counter {
  private var value = 0
  def increment() {
    if (value < Int.MaxValue) {
      value += 1
    }
  }
  def current() = value
}

val counter = new Counter

var i = 0L
while (i < Int.MaxValue.toLong + 4)  {
  counter.increment()
  i += 1
}

assert(counter.current() == Int.MaxValue)*/

// 2
class BankAccount(private var total : BigDecimal) {
  def withdraw(amount : BigDecimal): Unit = {
    if (amount < total) {
      total -= amount
    }
  }

  def deposit(amount : BigDecimal): Unit = {
      total += amount
  }

  def amount = total
}

val bankAccount = new BankAccount(100.00)
bankAccount.withdraw(100.01)
assert(bankAccount.amount == 100.00)

bankAccount.withdraw(80.01)
assert(bankAccount.amount == 19.99)

bankAccount.deposit(30.00)
assert(bankAccount.amount == 49.99)

// 3
class Time(val hours: Int, val minutes: Int) {
  if (hours > 23) throw new RuntimeException
  if (minutes > 60) throw new RuntimeException
  if (hours < 0) throw new RuntimeException
  if (minutes < 0) throw new RuntimeException

  def before(other: Time): Boolean = {
    var result = false
    if (this.hours < other.hours) {
      result = true
    } else if (other.hours == this.minutes) {
      result = this.minutes < other.minutes
    }
    result
  }
}

val time1: Time = new Time(12, 12)
val time2: Time = new Time(12, 12)
val time3: Time = new Time(12, 13)
val time4: Time = new Time(12, 11)
val time5: Time = new Time(1, 11)

assert(!time1.before(time2))
assert(time1.before(time3))
assert(!time1.before(time4))
assert(time5.before(time4))

// 4
class Time2(val hours: Int, val minutes: Int) {
  if (hours > 23) throw new RuntimeException
  if (minutes > 60) throw new RuntimeException
  if (hours < 0) throw new RuntimeException
  if (minutes < 0) throw new RuntimeException
  private val internal : Int = hours * 60 + minutes
  def before(other: Time2): Boolean = {
    this.internal < other.internal
  }
}

val time21: Time2 = new Time2(12, 12)
val time22: Time2 = new Time2(12, 12)
val time23: Time2 = new Time2(12, 13)
val time24: Time2 = new Time2(12, 11)
val time25: Time2 = new Time2(1, 11)

assert(!time21.before(time22))
assert(time21.before(time23))
assert(!time21.before(time24))
assert(time25.before(time24))

// 5
class Student(@BeanProperty var id : Long, @BeanProperty var name : String) {}

val st1 = new Student(1, "Eugene Konopaka")
val st2 = new Student(2, "Chuck Norris")

//scala> :javap -private Student
//Compiled from "<console>"
//public class $line7.$read$$iw$$iw$Student {
//  private long id;
//  private java.lang.String name;
//  public long id();
//  public void id_$eq(long);
//  public java.lang.String name();
//  public void name_$eq(java.lang.String);
//  public long getId();
//  public void setId(long);
//  public java.lang.String getName();
//  public void setName(java.lang.String);
//  public $line7.$read$$iw$$iw$Student(long, java.lang.String);
//}

// With bean setters
st1.getName
st2.getId

// With scala setters
st1.id
st1.id=3
st1.id



