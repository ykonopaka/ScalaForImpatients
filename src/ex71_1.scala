package com {
  class A {}
  package horstmann {
    class B {}
    package impatient {
      class C {
        val b : B = new B
        val a : A = new A
        val c : C = new C
      }
    }
  }
}
