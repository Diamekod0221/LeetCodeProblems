import SameTree.isSameTree

import scala.collection.mutable
///Given the roots of two binary trees p and q, write a function to check if they are the same or not.
//
//Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
//
//Example 1:
//
//Input: p = [1,2,3], q = [1,2,3]
//Output: true

object SameTree {
  def isSameTree(p: TreeNode, q: TreeNode): Boolean = (p,q) match {
    case (null,null) => true
    case (_, null) => false
    case (null, _) => false
    case (a,b) => isSameTree(a.left, b.left) && a.value == b.value && isSameTree(a.right, b.right)
  }
}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SameTreeSpec extends AnyFlatSpec with Matchers {

  "isSameTree" should "return true for p = [1,2,3] and q = [1,2,3]" in {
    isSameTree(
      TreeNode(1, TreeNode(2), TreeNode(3)),
      TreeNode(1, TreeNode(2), TreeNode(3))
    ) shouldBe true
  }

  it should "return false for p = [1,2] and q = [1,null,2]" in {
    isSameTree(
      TreeNode(1, TreeNode(2), null),
      TreeNode(1, null, TreeNode(2))
    ) shouldBe false
  }

  it should "return false for p = [1,2,1] and q = [1,1,2]" in {
    isSameTree(
      TreeNode(1, TreeNode(2), TreeNode(1)),
      TreeNode(1, TreeNode(1), TreeNode(2))
    ) shouldBe false
  }

  it should "return true for two empty trees" in {
    isSameTree(null, null) shouldBe true
  }

  it should "return false when p is non-empty and q is null" in {
    isSameTree(TreeNode(1), null) shouldBe false
  }

  it should "return false when p is null and q is non-empty" in {
    isSameTree(null, TreeNode(1)) shouldBe false
  }

  it should "return true for identical single-node trees" in {
    isSameTree(TreeNode(42), TreeNode(42)) shouldBe true
  }

  it should "return false for single-node trees with different values" in {
    isSameTree(TreeNode(1), TreeNode(2)) shouldBe false
  }

  it should "return true for identical deep trees" in {
    isSameTree(
      TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3)),
      TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3))
    ) shouldBe true
  }

  it should "return false when trees differ only in a leaf value" in {
    isSameTree(
      TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3)),
      TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(99)), TreeNode(3))
    ) shouldBe false
  }

  it should "return false for same values but different structure" in {
    isSameTree(
      TreeNode(1, TreeNode(2, TreeNode(3), null), null),
      TreeNode(1, TreeNode(2), TreeNode(3))
    ) shouldBe false
  }

  it should "return true for identical left-skewed trees" in {
    isSameTree(
      TreeNode(1, TreeNode(2, TreeNode(3, TreeNode(4), null), null), null),
      TreeNode(1, TreeNode(2, TreeNode(3, TreeNode(4), null), null), null)
    ) shouldBe true
  }

  it should "return false for trees with only the root value differing" in {
    isSameTree(
      TreeNode(1, TreeNode(2), TreeNode(3)),
      TreeNode(99, TreeNode(2), TreeNode(3))
    ) shouldBe false
  }

  it should "return true for identical trees with negative values" in {
    isSameTree(
      TreeNode(-1, TreeNode(-2), TreeNode(-3)),
      TreeNode(-1, TreeNode(-2), TreeNode(-3))
    ) shouldBe true
  }
}
