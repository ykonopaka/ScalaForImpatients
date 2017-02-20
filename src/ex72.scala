package test {

  class Puzzle {
    val bzz = java.nio.file.Paths.get(".")
  }

  // Comment and uncomment and observe different results
  package java.nio.file {
    object Paths {
      def get(a: String) : String = {
        a
      }
    }
  }
}
