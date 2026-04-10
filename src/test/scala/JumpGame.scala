import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
///You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
//
//Return true if you can reach the last index, or false otherwise.
//
//
//
//Example 1:
//
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//Example 2:
//
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

object JumpGame {
    def canJumpRec(nums: Array[Int]): Boolean = {
      jump(0, nums)
    }

    def jump(i: Int, nums: Array[Int]): Boolean = {
      i match {
        case i if i == nums.length - 1 => true
        case j if nums(j) == 0 || j >= nums.length => false
        case _ => (1 to nums(i)).exists(j => jump(i + j, nums))
      }
    }

  def canJump(nums: Array[Int]): Boolean = {
    var target = nums.length - 1
    for(i <- nums.length -2 to 0 by -1) {
      if(i + nums(i) >= target) target = i
    }
    target == 0
  }

  def canJumpBackwards(i: Int, target: Int, nums:Array[Int]): Boolean = {
    target match {
      case 0 => true
      case _ if i < 0 => false
      case _ => if(i + nums(i) >= target) canJumpBackwards(i-1, i, nums) else canJumpBackwards(i-1, target, nums)
    }
  }
}

class JumpGameSpec extends AnyFlatSpec {
  "canJump" should "handle large inputs" in {true

  }
}
