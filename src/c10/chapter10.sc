import java.awt.Point
import java.awt.geom.Ellipse2D

import c10.DefaultCaesarCipherLogger

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