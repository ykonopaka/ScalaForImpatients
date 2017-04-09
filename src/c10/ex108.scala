package c10

/**
  * Created by Eugene on 4/9/2017.
  */
trait DeviceLike {
  def turnOn

  def turnOff
}

trait PhoneLike extends DeviceLike {
  val hasFlash: Boolean = false
}

abstract class NokiaPhone extends {
  override val hasFlash = true
} with PhoneLike {
  var unbreakable: Boolean
}

class Nokia3310 extends NokiaPhone {
  override var unbreakable: Boolean = true

  override def turnOn = {
    println("Once you turned me on. No one can turn me off")
  }

  override def turnOff = {
    println("No one, I said!")
  }
}

trait MobileOsLike extends DeviceLike {
  var os: String
  var deviceType: String
}

trait TabletLike {
  var deviceType = "tablet"
  var bigScreen = true
}

trait SmartPhoneLike {
  var deviceType = "smartphone"
}

abstract class Iphone extends {
  var os: String = "ios"
} with SmartPhoneLike with MobileOsLike

abstract class Ipad extends {
  var os: String = "ios"
} with TabletLike with MobileOsLike

class Iphone6 extends Iphone {
  override def turnOn = {
    println(f"I am ${os} on ${deviceType}. Buy me")
  }

  override def turnOff = {
    println(f"I am ${os}. Buy and bye")
  }
}

class Ipad2 extends Ipad {
  override def turnOn = {
    println("Big Screen")
    println(f"I am ${os} on ${deviceType}. Buy me")
  }

  override def turnOff = {
    println(f"I am ${os}. Buy and bye")
  }
}

abstract class AndroidPhone extends MobileOsLike

class Nexus5 extends AndroidPhone with SmartPhoneLike with MobileOsLike {
  override var os: String = "android"

  override def turnOn = {
    println(f"I am ${os} from Google")
  }

  override def turnOff = {
    println(f"Shutting down")
  }
}

trait DeveloperMode extends DeviceLike {
  abstract override def turnOn = {
    println("DevMode ON")
    super.turnOn
  }

  abstract override def turnOff = {
    println("DevMode OFF")
    super.turnOff
  }
}

trait EmulationMode extends DeviceLike {
  abstract override def turnOn = {
    println("EmulationMode ON")
    super.turnOn
  }

  abstract override def turnOff = {
    println("EmulationMode OFF")
    super.turnOff
  }
}

class ChinesePhone extends AndroidPhone {
  override var deviceType: String = "smartphone"
  override var os: String = "noname"

  override def turnOn = {
    println("Chinese device on")
  }

  override def turnOff = {
    println("Chinese device of")
  }
}

