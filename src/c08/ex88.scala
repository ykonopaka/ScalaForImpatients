package c08

/**
  * Created by Eugene on 2/26/2017.
  */
class Person(val name: String)  {
  override def toString: String = s"${getClass.getName}[name=$name]"
}

class SecretAgent(codename: String) extends Person(codename)  {
  override val name = "secret"
  override val toString = "secret"
}

//λ D:\Work\Java\jdk1.8.0_60\bin\javap -private Person.class
//Compiled from "ex_88.scala"
//public class Person {
//  private final java.lang.String name;
//  public java.lang.String name();
//  public java.lang.String toString();
//  public Person(java.lang.String);
//}
//
//C:\Users\Eugene\IdeaProjects\ScalaForImpatients\out\production\ScalaForImpatients (master)
//λ D:\Work\Java\jdk1.8.0_60\bin\javap -private SecretAgent.class
//Compiled from "ex_88.scala"
//public class SecretAgent extends Person {
//  private final java.lang.String name;
//  private final java.lang.String toString;
//  public java.lang.String name();
//  public java.lang.String toString();
//  public SecretAgent(java.lang.String);
//}
