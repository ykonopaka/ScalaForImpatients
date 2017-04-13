import java.awt.Point
import java.awt.geom.Ellipse2D
import java.io._
import java.nio.file.Paths

import c10._

// 1
trait RectangleLike  {
  def getX(): Double
  def getY(): Double
  def getWidth(): Double
  def getHeight(): Double
  def setFrame(x: Double, y: Double, width: Double, height: Double)

  def translate(x: Double, y: Double) = {
    setFrame(getX() + x, getY() + y, getWidth(), getHeight())
  }

  def grow(x: Double, y: Double) = {
    setFrame(getX() - x, getY() - y, getWidth() + 2 * x, getHeight() + 2 * y)
  }
}

val egg = new Ellipse2D.Double( 5, 10, 20, 30) with RectangleLike
egg.translate( 10, -10)
egg.grow( 10, 20)

// 2
class OrderedPoint(val x: Int, val y: Int) extends Ordered[java.awt.Point] {
  private val p = new java.awt.Point(x, y)

  override def compare(that: Point): Int = {
    if (p.x == that.x && p.y == that.y) 0
    else if (p.x < that.x || (p.x == that.x && p.y < that.y)) -1
    else 1
  }
}

// 3

// The traits look like:

/*
BitSet << SortedSet, BitSetLike
SortedSet << Set, SortedSetLike
SortedSetLike << Sorted, SetLike
SetLike << Set
BitSetLike << SortedSet, SortedSetLike
Set << Iterable, GenSet, GenericSetTemplate, SetLike
Iterable << Traversable, GenIterable, GenericTraversableTemplate, IterableLike
GenSet << GenSetLike, GenIterable, GenericSetTemplate
GenericSetTemplate << GenericTraversableTemplate
SetLike << scala.collection.SetLike, Scriptable, Builder, Growable, Shrinkable, Cloneable, Parallelizable
Traversable << TraversableLike, GenTraversable, TraversableOnce, GenericTraversableTemplate
GenIterable << GenIterableLike, GenTraversable, GenericTraversableTemplate
GenericTraversableTemplate << HasNewBuilder
HasNewBuilder << Any
IterableLike << extends Any, Equals, TraversableLike, GenIterableLike
GenSetLike << GenIterableLike, Equals, Parallelizable
GenIterable << GenIterableLike, GenTraversable, GenericTraversableTemplate
GenericSetTemplate << GenericTraversableTemplate
scala.collection.SetLike << IterableLike, GenSetLike, Subtractable, Parallelizable
Builder << Growable
Growable << Clearable
Parallelizable << Any
TraversableLike << Any, HasNewBuilder, FilterMonadic, TraversableOnce, GenTraversableLike, Parallelizable
GenTraversable << GenTraversableLike, GenTraversableOnce, GenericTraversableTemplate
TraversableOnce << Any, GenTraversableOnce
GenIterableLike << Any, GenTraversableLike
Equals << Any
FilterMonadic << Any
GenTraversableLike << Any, GenTraversableOnce, Parallelizable
GenTraversableOnce << Any
*/

// Then the hierarchy is:

/*
BitSet <<
	SortedSet <<
		Set <<
			Iterable <<
				Traversable <<
					TraversableLike <<
						Any,
						HasNewBuilder,
						FilterMonadic <<
							Any,
						TraversableOnce <<
							Any,
							GenTraversableOnce <<
								Any,
						GenTraversableLike <<
							Any,
						Parallelizable,
					GenTraversable <<
						GenTraversableLike <<
							Any,
						GenTraversableOnce <<
							Any,
						GenericTraversableTemplate,
					TraversableOnce <<
						Any,
						GenTraversableOnce <<
							Any,
					GenericTraversableTemplate <<
						HasNewBuilder,
				GenIterable <<
					GenIterableLike <<
						Any,
						GenTraversableLike <<
							Any,
					GenTraversable <<
						GenTraversableLike <<
							Any,
						GenTraversableOnce <<
							Any,
						GenericTraversableTemplate,
					GenericTraversableTemplate <<
						HasNewBuilder,
				GenericTraversableTemplate <<
					HasNewBuilder,
				IterableLike <<
					Any,
					Equals <<
						Any,
					TraversableLike <<
						Any,
						HasNewBuilder,
						FilterMonadic <<
							Any,
						TraversableOnce <<
							Any,
							GenTraversableOnce <<
								Any,
						GenTraversableLike <<
							Any,
						Parallelizable,
					GenIterableLike <<
						Any,
						GenTraversableLike <<
							Any,
			GenSet <<
				GenSetLike <<
					GenIterableLike <<
						Any,
						GenTraversableLike <<
							Any,
					Equals <<
						Any,
					Parallelizable <<
						Any,
				GenIterable <<
					GenIterableLike <<
						Any,
						GenTraversableLike <<
							Any,
					GenTraversable <<
						GenTraversableLike <<
							Any,
						GenTraversableOnce <<
							Any,
						GenericTraversableTemplate,
					GenericTraversableTemplate <<
						HasNewBuilder,
				GenericSetTemplate <<
					GenericTraversableTemplate <<
						HasNewBuilder,
			GenericSetTemplate <<
				GenericTraversableTemplate <<
					HasNewBuilder,
			SetLike <<
				scala.collection.SetLike <<
					IterableLike  <<
						Any,
						Equals <<
							Any,
						TraversableLike <<
							Any,
							HasNewBuilder,
							FilterMonadic <<
								Any,
							TraversableOnce <<
								Any,
								GenTraversableOnce <<
									Any,
							GenTraversableLike <<
								Any,
							Parallelizable,
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
					GenSetLike  <<
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
						Equals <<
							Any,
						Parallelizable <<
							Any,
					Subtractable,
					Parallelizable,
				Scriptable,
				Builder <<
					Growable <<
						Clearable,
				Growable <<
					Clearable,
				Shrinkable,
				Cloneable,
				Parallelizable,
		SortedSetLike <<
			Sorted,
			SetLike <<
				scala.collection.SetLike <<
					IterableLike  <<
						Any,
						Equals <<
							Any,
						TraversableLike <<
							Any,
							HasNewBuilder,
							FilterMonadic <<
								Any,
							TraversableOnce <<
								Any,
								GenTraversableOnce <<
									Any,
							GenTraversableLike <<
								Any,
							Parallelizable,
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
					GenSetLike  <<
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
						Equals <<
							Any,
						Parallelizable <<
							Any,
					Subtractable,
					Parallelizable,
				Scriptable,
				Builder <<
					Growable <<
						Clearable,
				Growable <<
					Clearable,
				Shrinkable,
				Cloneable,
				Parallelizable,
	BitSetLike <<
		SortedSet  <<
			Set <<
				Iterable <<
					Traversable <<
						TraversableLike <<
							Any,
							HasNewBuilder,
							FilterMonadic <<
								Any,
							TraversableOnce <<
								Any,
								GenTraversableOnce <<
									Any,
							GenTraversableLike <<
								Any,
							Parallelizable,
						GenTraversable <<
							GenTraversableLike <<
								Any,
							GenTraversableOnce <<
								Any,
							GenericTraversableTemplate,
						TraversableOnce <<
							Any,
							GenTraversableOnce <<
								Any,
						GenericTraversableTemplate <<
							HasNewBuilder,
					GenIterable <<
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
						GenTraversable <<
							GenTraversableLike <<
								Any,
							GenTraversableOnce <<
								Any,
							GenericTraversableTemplate,
						GenericTraversableTemplate <<
							HasNewBuilder,
					GenericTraversableTemplate <<
						HasNewBuilder,
					IterableLike <<
						Any,
						Equals <<
							Any,
						TraversableLike <<
							Any,
							HasNewBuilder,
							FilterMonadic <<
								Any,
							TraversableOnce <<
								Any,
								GenTraversableOnce <<
									Any,
							GenTraversableLike <<
								Any,
							Parallelizable,
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
				GenSet <<
					GenSetLike <<
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
						Equals <<
							Any,
						Parallelizable <<
							Any,
					GenIterable <<
						GenIterableLike <<
							Any,
							GenTraversableLike <<
								Any,
						GenTraversable <<
							GenTraversableLike <<
								Any,
							GenTraversableOnce <<
								Any,
							GenericTraversableTemplate,
						GenericTraversableTemplate <<
							HasNewBuilder,
					GenericSetTemplate <<
						GenericTraversableTemplate <<
							HasNewBuilder,
				GenericSetTemplate <<
					GenericTraversableTemplate <<
						HasNewBuilder,
				SetLike <<
					scala.collection.SetLike <<
						IterableLike  <<
							Any,
							Equals <<
								Any,
							TraversableLike <<
								Any,
								HasNewBuilder,
								FilterMonadic <<
									Any,
								TraversableOnce <<
									Any,
									GenTraversableOnce <<
										Any,
								GenTraversableLike <<
									Any,
								Parallelizable,
							GenIterableLike <<
								Any,
								GenTraversableLike <<
									Any,
						GenSetLike  <<
							GenIterableLike <<
								Any,
								GenTraversableLike <<
									Any,
							Equals <<
								Any,
							Parallelizable <<
								Any,
						Subtractable,
						Parallelizable,
					Scriptable,
					Builder <<
						Growable <<
							Clearable,
					Growable <<
						Clearable,
					Shrinkable,
					Cloneable,
					Parallelizable,
			SortedSetLike <<
				Sorted,
				SetLike <<
					scala.collection.SetLike <<
						IterableLike  <<
							Any,
							Equals <<
								Any,
							TraversableLike <<
								Any,
								HasNewBuilder,
								FilterMonadic <<
									Any,
								TraversableOnce <<
									Any,
									GenTraversableOnce <<
										Any,
								GenTraversableLike <<
									Any,
								Parallelizable,
							GenIterableLike <<
								Any,
								GenTraversableLike <<
									Any,
						GenSetLike  <<
							GenIterableLike <<
								Any,
								GenTraversableLike <<
									Any,
							Equals <<
								Any,
							Parallelizable <<
								Any,
						Subtractable,
						Parallelizable,
					Scriptable,
					Builder <<
						Growable <<
							Clearable,
					Growable <<
						Clearable,
					Shrinkable,
					Cloneable,
					Parallelizable,
		SortedSetLike <<
				Sorted,
				SetLike <<
					scala.collection.SetLike <<
						IterableLike  <<
							Any,
							Equals <<
								Any,
							TraversableLike <<
								Any,
								HasNewBuilder,
								FilterMonadic <<
									Any,
								TraversableOnce <<
									Any,
									GenTraversableOnce <<
										Any,
								GenTraversableLike <<
									Any,
								Parallelizable,
							GenIterableLike <<
								Any,
								GenTraversableLike <<
									Any,
						GenSetLike  <<
							GenIterableLike <<
								Any,
								GenTraversableLike <<
									Any,
							Equals <<
								Any,
							Parallelizable <<
								Any,
						Subtractable,
						Parallelizable,
					Scriptable,
					Builder <<
						Growable <<
							Clearable,
					Growable <<
						Clearable,
					Shrinkable,
					Cloneable,
					Parallelizable,
*/

// Linearization of the traits.

/*
BitSet >>
BitSetLike >>
SortedSet >>
Set >>
Iterable >>
Traversable >>
GenSet >>
GenIterable >>
GenTraversable >>
GenericSetTemplate >>
GenericTraversableTemplate >>
SortedSetLike >>
Sorted >>
SetLike >>
scala.collection.SetLike >>
IterableLike >>
TraversableLike >>
HasNewBuilder >>
FilterMonadic >>
TraversableOnce >>
GenTraversableOnce >>
GenSetLike >>
GenIterableLike >>
GenTraversableLike >>
Equals >>
Subtractable >>
Scriptable >>
Builder >>
Growable >>
Clearable >>
Shrinkable >>
Cloneable >>
Parallelizable >>
Any
*/

// 4
val text = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG"

val loggerDefault = new DefaultCaesarCipherLogger
val loggerCustom = new {override val key = 5} with DefaultCaesarCipherLogger
val anotherLoggerCustom = new {override val key = -3} with DefaultCaesarCipherLogger

loggerDefault.log(text)
loggerCustom.log(text)
anotherLoggerCustom.log(text)

// 5
val pointWithListenerSupport = new java.awt.Point() with PropertyChangeSupportLike
pointWithListenerSupport.getPropertyChangeListeners

// 6
// The design on the picture has multiple inheritance which is not allowed in Java
// JContainer extends both Container and JComponent. It is not possible so
// the hierarchy should be simpler to have only one parent class and it is Component
// then we have Container and then the rest part of hierarchy
// In scala we could define traits ComponentLike and ContainerLike and mix in
// these properties to awt elements appropriately

// 7
val acc = new SavingsAccount

// An unclear exception thrown

/*
java.lang.AbstractMethodError: Method c10/SavingsAccount.c10$ConsoleLogger$_setter_$pre_$eq(Ljava/lang/String;)V is abstract
at c10.SavingsAccount.c10$ConsoleLogger$_setter_$pre_$eq(chapter10.sc2)
at c10.ConsoleLogger.$init$(chapter10.sc2:3)
at c10.SavingsAccount.<init>(chapter10.sc2:2)
at c10.A$A4$A$A4.acc$lzycompute(chapter10.sc2:70)
at c10.A$A4$A$A4.acc(chapter10.sc2:70)
at c10.A$A4$A$A4.get$$instance$$acc(chapter10.sc2:69)
at #worksheet#.#worksheet#(chapter10.sc2:615)
*/

// 8
val nokia3310 = new Nokia3310
val iphone6 = new Iphone6
val nexus5 = new Nexus5 with EmulationMode with DeveloperMode

val nonameChinesePhone = new ChinesePhone with DeveloperMode with EmulationMode

nokia3310.turnOn
iphone6.turnOn
nexus5.turnOn
nonameChinesePhone.turnOn

// 9
val currDir = Paths.get("c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src")
val input = new FileInputStream(currDir.resolve("c04/words.txt").toFile) with BufferedLike

input.read()

// 10
val input2 = new FileInputStream(currDir.resolve("c04/words.txt").toFile) with BufferedLike2 with ConsoleLogger2
input2.read()

// 11
val input3 = new FileInputStream(currDir.resolve("c04/words.txt").toFile)
val iterableInput = new IterableInputStream(input3)
for (d <- iterableInput) println(d)

// 12
// class LoggedEntity1 extends Entity with TimestampLogger with ShortLogger
/*
public class c10.LoggedEntity1 extends c10.Entity implements c10.TimestampLogger,c10.ShortLogger {
  public void c10$ShortLogger$$super$log(java.lang.String);
  Code:
  0: aload_0
  1: aload_1
  2: invokestatic  #19                 // InterfaceMethod c10/TimestampLogger.log$:(Lc10/TimestampLogger;Ljava/lang/String;)V
  5: return

  public void log(java.lang.String);
  Code:
  0: aload_0
  1: aload_1
  2: invokestatic  #26                 // InterfaceMethod c10/ShortLogger.log$:(Lc10/ShortLogger;Ljava/lang/String;)V
  5: return

  public void c10$TimestampLogger$$super$log(java.lang.String);
  Code:
  0: aload_0
  1: aload_1
  2: invokestatic  #32                 // InterfaceMethod c10/ConsoleLoggerExample.log$:(Lc10/ConsoleLoggerExample;Ljava/lang/String;)V
  5: return

  public c10.LoggedEntity1();
  Code:
  0: aload_0
  1: invokespecial #36                 // Method c10/Entity."<init>":()V
  4: aload_0
  5: invokestatic  #40                 // InterfaceMethod c10/ConsoleLoggerExample.$init$:(Lc10/ConsoleLoggerExample;)V
  8: aload_0
  9: invokestatic  #43                 // InterfaceMethod c10/TimestampLogger.$init$:(Lc10/TimestampLogger;)V
  12: aload_0
  13: invokestatic  #46                 // InterfaceMethod c10/ShortLogger.$init$:(Lc10/ShortLogger;)V
  16: return
}*/

// Both traits have set of methods e.g ShortLogger::
//
// public abstract void c10$ShortLogger$$super$log(java.lang.String);
// public static void log$(c10.ShortLogger, java.lang.String);
// public void log(java.lang.String);
//
// The same for TimestampLogger
//
// The Entity object implements both the interfaces:
// public void c10$ShortLogger$$super$log(java.lang.String);
// public void log(java.lang.String);
// public void c10$TimestampLogger$$super$log(java.lang.String);
//
// We see that log(String) calls ShortLogger.log() first, then c10$ShortLogger$$super$log calls TimestampLogger.log()
// And finally ConsoleLoggerExample.log() is called