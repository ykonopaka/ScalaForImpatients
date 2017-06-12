// 1
// see ex15_1.scala

// 2
// see ex15_2.scala

// 3
// @transient uses @field
// @compileTimeOnly uses @getter @setter @beanGetter @beanSetter @companionClass @companionMethod

// 4
// see ex15_4.scala and SummerExample.java

// 5
// see ex15_6.scala

// 6
// the second thread without volatile sometimes sees the
// outdated value:
// I'm the first thread and I'm done!
// I'm the second thread. Waiting for volatile to be true
// I'm the second thread and I'm done!
//
// With volatile it does not happen
// I'm the first thread and I'm done!
// I'm the second thread. Waiting for volatile to be true
// I'm the second thread and I'm done!

// 7
// Remove final from OverridenParent and code will not compile

// 8
// It generated all seven methods with primitive types
/*
scala> object AllDifferent { def allDifferent[@ specialized T]( x: T, y: T, z: T) : Unit = {} }
defined object AllDifferent

scala> :javap -private AllDifferent.class
Compiled from "<console>"
public class $line8.$read$$iw$$iw$AllDifferent$ {
  public static $line8.$read$$iw$$iw$AllDifferent$ MODULE$;
  public static {};
  public <T> void allDifferent(T, T, T);
  public void allDifferent$mZc$sp(boolean, boolean, boolean);
  public void allDifferent$mBc$sp(byte, byte, byte);
  public void allDifferent$mCc$sp(char, char, char);
  public void allDifferent$mDc$sp(double, double, double);
  public void allDifferent$mFc$sp(float, float, float);
  public void allDifferent$mIc$sp(int, int, int);
  public void allDifferent$mJc$sp(long, long, long);
  public void allDifferent$mSc$sp(short, short, short);
  public void allDifferent$mVc$sp(scala.runtime.BoxedUnit, scala.runtime.BoxedUnit, scala.runtime.BoxedUnit);
  public $line8.$read$$iw$$iw$AllDifferent$();
}
*/

// 9
/*
public class scala.collection.immutable.Range extends scala.collection.AbstractSeq<java.lang.Object> implements scala.collection.immutable.IndexedSeq<java.lang.Object>, scala.collection.CustomParallelizable<java.lang.Object, scala.collection.parallel.immutable.ParRange>, scala.Serializable {
...
  public final <U> void foreach(scala.Function1<java.lang.Object, U>);
  public final void foreach$mVc$sp(scala.Function1<java.lang.Object, scala.runtime.BoxedUnit>);
}
 */
// foreach is consumer which takes function which consumer a value and returns unit.
// Function1 can take any type and produce any type but for each should take any type and consume it
// not returning anything

// 10
// See ex15_10.scala
// With assertion (option -Xelide-below 800) in scala compiler when running a program there is an exception thrown
// Without assertion (option -Xelide-below 50000) program executes silently