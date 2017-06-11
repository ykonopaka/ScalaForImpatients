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