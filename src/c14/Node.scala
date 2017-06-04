package c14

/**
  * Created by Eugene on 6/4/2017.
  */
sealed abstract class BinaryTree

case class Leaf(value: Int) extends BinaryTree

case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree