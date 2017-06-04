package c14

/**
  * Created by Eugene on 6/4/2017.
  */
sealed abstract class OperationBinaryTree

case class OperationLeaf(value: Int) extends OperationBinaryTree

case class OperationNode(op: Operation.Value, nodes: OperationBinaryTree*) extends OperationBinaryTree

object Operation extends Enumeration {
  val + = Value('+')
  val - = Value('-')
  val * = Value('*')
  val / = Value('/')
}
