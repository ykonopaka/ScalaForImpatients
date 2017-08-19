package c19

import scala.collection.mutable.ArrayBuffer
import scala.language.existentials
import scala.language.reflectiveCalls

class Network {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Network#Member]

    def canEqual(other: Any): Boolean = other.isInstanceOf[Member]

    override def equals(other: Any): Boolean = other match {
      case that: Member =>
        (that canEqual this) &&
          contacts == that.contacts &&
          name == that.name
      case _ => false
    }

    override def hashCode(): Int = {
      val state = Seq(contacts, name)
      state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String): Member = {
    val m = new Member(name)
    members += m
    m
  }
}

object Network {
  type NetworkMember = n.Member forSome { val n: Network }
}

