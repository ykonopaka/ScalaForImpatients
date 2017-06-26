import java.time.LocalTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, _}
import scala.concurrent.duration._

// 1
println(s"First: The time is ${LocalTime.now}")

for (n1 <- Future {
  Thread.sleep(1000); 2
};
     n2 <- Future {
       Thread.sleep(1000); 40
     }) {
  println(n1 + n2)
  println(s"First: The time is ${LocalTime.now}")
}

// It looks like that Future {Thread.sleep(1000); 2} is equivalent
// to def d1 = Future {Thread.sleep(1000); 2}
// That's why it is run one after another

println(s"Second: The time is ${LocalTime.now}")
val res = Future {
  Thread.sleep(1000); 2
}
  .flatMap(n1 => Future {
    Thread.sleep(1000); 40
  }
    .map(n2 => {
      n1 + n2
    }));

Await.ready(res, 3.seconds)
println(s"Second: The time is ${LocalTime.now}")

