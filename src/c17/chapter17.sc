import java.time.LocalTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, _}

// 1
println(s"First: The time is ${LocalTime.now}")

for (n1 <- Future {
  Thread.sleep(1000);
  2
};
     n2 <- Future {
       Thread.sleep(1000);
       40
     }) {
  println(n1 + n2)
  println(s"First: The time is ${LocalTime.now}")
}

// It looks like that Future {Thread.sleep(1000); 2} is equivalent
// to def d1 = Future {Thread.sleep(1000); 2}
// That's why it is run one after another

println(s"Second: The time is ${LocalTime.now}")
val res = Future {
  Thread.sleep(1000);
  2
}
  .flatMap(n1 => Future {
    Thread.sleep(1000);
    40
  }
    .map(n2 => {
      n1 + n2
    }));

Await.ready(res, 3.seconds)
println(s"Second: The time is ${LocalTime.now}")

// 2
def doInOrder[T, U, V](t: T, f: T => Future[U], g: U => Future[V]): Future[V] = {
  f.apply(t).flatMap(v1 => g.apply(v1))
}

val s = doInOrder[Int, String, Double](3, (i: Int) => Future {
  "s"
}, (s: String) => Future {
  3.14D
})
Await.ready(s, 3.seconds)

// 3
def doInOrderMany[T](t: T, seq: Seq[T => Future[T]]): Future[T] = {
  var f = seq.head.apply(t)
  for (o <- seq.tail) {
    f = f.flatMap(g => o.apply(g))
  }
  f
}

val m = doInOrderMany(3, Seq((i: Int) => Future {
  3
}, (i: Int) => Future {
  5
}, (i: Int) => Future {
  8
}))
Await.ready(m, 3.seconds)

// 4
def doTogether[T, U, V](t: T, f: T => Future[U], g: T => Future[V]): Future[(U, V)] = {
  def future1 = Future {
    f.apply(t)
  }

  def future2 = Future {
    g.apply(t)
  }

  future1.zip(future2).map(f => (f._1.value.get.get, f._2.value.get.get))
}

val together = doTogether(3, (i: Int) => Future {
  3
}, (i: Int) => Future {
  5
})

Await.ready(together, 3.seconds)

// 5
def doTogetherSeq[T](seq: Seq[Future[T]]): Future[Seq[T]] = {
  val p = Promise[Seq[T]]()
  p.success(Seq.empty[T])

  val f: Future[Seq[T]] = p.future

  seq.foldLeft(f) {
    (accumulator, future) =>
      for {lst <- accumulator; a <- future} yield {
        lst ++ Seq[T](a)
      }
  }
}

def func1 = Future {
  Thread.sleep(1000);
  2
}

def func2 = Future {
  Thread.sleep(1000);
  2
}

println(s"Exercise 5: The time is ${LocalTime.now}")
val s5 = doTogetherSeq(Seq(func1, func2))

Await.ready(s5, 3.seconds)
println(s"Exercise 5: The time is ${LocalTime.now}")
println(s5)

// 6
// See PasswordWaiter.scala

// 7
// See PrimeNumberCalculator.scala

// 8
// See URLHyperlinkDisplayer.scala

// 9
// See URLHeaderReader.scala
// http://www.google.com => Future(Success(Map(sffe -> 2, GSE -> 4, YouTubeFrontEnd -> 1, gws -> 3, Search-History HTTP Server -> 1, ESF -> 2)))

// 10
// See URLHeaderReaderCollection
// http://www.google.com => {gws=3, YouTubeFrontEnd=1, sffe=2, Search-History HTTP Server=1, GSE=4, ESF=2}

// 11
// See Sleeper.scala
// When it is four in parallel:
// Current time is Mon Jul 17 22:54:57 CEST 2017
// Current time is Mon Jul 17 22:54:57 CEST 2017
// Current time is Mon Jul 17 22:54:57 CEST 2017
// Current time is Mon Jul 17 22:54:57 CEST 2017
// Future(Success(List((), (), (), ())))
//
// When it is 40 in parallel:
// It executes a part of four and then another part of four till the end
// Current time is Mon Jul 17 22: 56: 44 CEST 2017
// Current time is Mon Jul 17 22: 56: 44 CEST 2017
// Current time is Mon Jul 17 22: 56: 44 CEST 2017
// Current time is Mon Jul 17 22: 56: 44 CEST 2017
// Current time is Mon Jul 17 22: 56: 54 CEST 2017
// Current time is Mon Jul 17 22: 56: 54 CEST 2017
// Current time is Mon Jul 17 22: 56: 54 CEST 2017
// Current time is Mon Jul 17 22: 56: 54 CEST 2017
// Current time is Mon Jul 17 22: 57: 04 CEST 2017
// Current time is Mon Jul 17 22: 57: 04 CEST 2017
// Current time is Mon Jul 17 22: 57: 04 CEST 2017
// Current time is Mon Jul 17 22: 57: 04 CEST 2017
// Current time is Mon Jul 17 22: 57: 14 CEST 2017
// Current time is Mon Jul 17 22: 57: 14 CEST 2017
// Current time is Mon Jul 17 22: 57: 14 CEST 2017
// Current time is Mon Jul 17 22: 57: 14 CEST 2017
// Current time is Mon Jul 17 22: 57: 24 CEST 2017
// Current time is Mon Jul 17 22: 57: 24 CEST 2017
// Current time is Mon Jul 17 22: 57: 24 CEST 2017
// Current time is Mon Jul 17 22: 57: 24 CEST 2017
// Current time is Mon Jul 17 22: 57: 34 CEST 2017
// Current time is Mon Jul 17 22: 57: 34 CEST 2017
// Current time is Mon Jul 17 22: 57: 34 CEST 2017
// Current time is Mon Jul 17 22: 57: 34 CEST 2017
// Current time is Mon Jul 17 22: 57: 44 CEST 2017
// Current time is Mon Jul 17 22: 57: 44 CEST 2017
// Current time is Mon Jul 17 22: 57: 44 CEST 2017
// Current time is Mon Jul 17 22: 57: 44 CEST 2017
// Current time is Mon Jul 17 22: 57: 54 CEST 2017
// Current time is Mon Jul 17 22: 57: 54 CEST 2017
// Current time is Mon Jul 17 22: 57: 54 CEST 2017
// Current time is Mon Jul 17 22: 57: 54 CEST 2017
// Current time is Mon Jul 17 22: 58: 04 CEST 2017
// Current time is Mon Jul 17 22: 58: 04 CEST 2017
// Current time is Mon Jul 17 22: 58: 04 CEST 2017
// Current time is Mon Jul 17 22: 58: 04 CEST 2017
// Current time is Mon Jul 17 22: 58: 14 CEST 2017
// Current time is Mon Jul 17 22: 58: 14 CEST 2017
// Current time is Mon Jul 17 22: 58: 14 CEST 2017
// Current time is Mon Jul 17 22: 58: 14 CEST 2017
// Future(Success(List((), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), ())))
//
// With cached thread pool everything is ready within 10 seconds:
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Current time is Mon Jul 17 23:02:31 CEST 2017
// Future(Success(List((), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), ())))

// 12
// See URLHeaderPromises
// Why would it not be a good idea to return a sequence of promises?
// Because there is no way to combine List of promises into one promise
// With futures it is possible
