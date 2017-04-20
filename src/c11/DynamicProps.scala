package c11

import scala.language.dynamics

/**
  * Created by Eugene on 4/20/2017.
  */
class DynamicProps(val props: java.util.Properties) extends Dynamic {
  def updateDynamic(name: String)(value: String): DynamicHelper = {
    DynamicHelper(name, props)
  }

  def selectDynamic(name: String): DynamicHelper = {
    DynamicHelper(name, props)
  }
}

class DynamicHelper(name: String, val props: java.util.Properties) extends Dynamic {

  val part: String = name

  def selectDynamic(name: String): DynamicHelper = {
    new DynamicHelper(this.name + "." + name, props)
  }

  def updateDynamic(name: String)(value: String): Unit = {
    props.setProperty(this.name + "." + name, value)
  }

  override def toString: String = props.getProperty(name)
}

object DynamicHelper {
  def apply(k: String, p: java.util.Properties) = new DynamicHelper(k, p)
}