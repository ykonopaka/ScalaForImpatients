package c14

;

/**
  * Created by Eugene on 6/4/2017.
  */
sealed abstract class ExtendedBinaryTree

case class ExtendedLeaf(value: Int) extends ExtendedBinaryTree

case class ExtendedNode(nodes: ExtendedBinaryTree*) extends ExtendedBinaryTree