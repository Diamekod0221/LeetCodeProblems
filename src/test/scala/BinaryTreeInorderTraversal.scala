import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
///Given the root of a binary tree, return the inorder traversal of its nodes' values.
//
//
//
//Example 1:
//
//Input: root = [1,null,2,3]
//
//Output: [1,3,2]
//
//Explanation:

/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
 * var value: Int = _value
 * var left: TreeNode = _left
 * var right: TreeNode = _right
 * }
 */

case class TreeNode(value: Int = 0, left: TreeNode = null, right: TreeNode = null)

object BinaryTreeInorderTraversal {

  def inorderTraversal(root: TreeNode): List[Int] = {
    if (root == null) List()
    else inorderTraversal(root.left) ++ List(root.value) ++ inorderTraversal(root.right)
  }

  def inorderTraversalStack(root: TreeNode): List[Int] = {
    val result = ListBuffer[Int]()
    val stack = mutable.Stack[TreeNode]()
    var curr = root

    while (stack.nonEmpty || curr != null) {
      if (curr != null) {
        stack.push(curr);
        curr = curr.left
      }
      else {
        curr = stack.pop
        result.append(curr.value)
        curr = curr.right
      }
    }
    result.toList
  }
}

class BinaryTreeSpec extends AnyFlatSpec {
  "inorderTraversal" should "handle [1,null,3,2] properly" in {
    val tree1 = TreeNode(1, null, TreeNode(2, TreeNode(3), null))

    BinaryTreeInorderTraversal.inorderTraversalStack(tree1) shouldBe List(1, 3, 2)
  }

  it should "handle next [1,2,3,4,5,null,8,null,null,6,7,9] properly" in {
    val tree2 = TreeNode(1,
      TreeNode(2,
        TreeNode(4),
        TreeNode(5, TreeNode(6), TreeNode(7))
      ),
      TreeNode(3,
        null,
        TreeNode(8, TreeNode(9), null)
      )
    )

    BinaryTreeInorderTraversal.inorderTraversalStack(tree2) shouldBe List(4, 2, 6, 5, 7, 1, 3, 9, 8)

  }
}
