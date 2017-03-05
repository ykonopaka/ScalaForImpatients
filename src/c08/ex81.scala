package c08

/**
  * Created by Eugene on 2/26/2017.
  */
class BankAccount(initBalance: Double) {
  protected var balance = initBalance

  def currentBalance = balance

  def deposit(amount: Double) = { balance += amount }

  def withdraw(amount: Double) = { balance -= amount }
}

class CheckingAccount(initBalance: Double) extends BankAccount(initBalance: Double) {
  override def deposit(amount: Double) = {
    if (amount > 0) { super.deposit(amount); fee }
  }

  override def withdraw(amount: Double) = {
    if (amount > 0) { super.withdraw(amount); fee }
  }

  private def fee = { balance -= 1 }
}

class SavingsAccount(initBalance: Double, monthlyFee: Double) extends BankAccount(initBalance: Double) {

  private var transationNumber: Int = 0
  private val fee = monthlyFee

  override def deposit(amount: Double) = {
    if (amount > 0) { super.deposit(amount); transationNumber += 1 }
  }

  override def withdraw(amount: Double) = {
    if (amount > 0) { super.withdraw(amount); transationNumber += 1 }
  }

  def earnMonthlyInterest = {
    if (transationNumber >= 3) { balance -= fee };
    transationNumber = 0
  }
}
