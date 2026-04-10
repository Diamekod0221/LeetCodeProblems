import TwoSum.twoSum
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

///Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//You can return the answer in any order.
//
//
//
//Example 1:
//
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
//Example 2:
//
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
//Example 3:
//
//Input: nums = [3,3], target = 6
//Output: [0,1]

object TwoSum {
  def twoSumMap(nums: Array[Int], target: Int): Array[Int] = {
      val zipped = nums.zipWithIndex
      val firstMatch = zipped.flatMap{ i =>
        zipped.toList.collectFirst{ case (v, ind) if v + i._1 == target && ind != i._2 => (i._2, ind)} }.head
      Array(firstMatch._1, firstMatch._2)}


  def twoSumIter(nums: Array[Int], target: Int): Array[Int] = {
    for (i <- 0 to nums.length - 2){
      for ( j <- i + 1 until nums.length){
        if ((nums(i) + nums(j)) == target) return Array(i,j)
      }
    }
    Array(-1,-1)
  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    @annotation.tailrec
    def move(i: Int, seen: Map[Int,Int]): Array[Int] =
      if(i == nums.length) Array(-1,-1)
      else seen.get(target - nums(i)) match {
        case Some(j) => Array(j, i)
        case None => move(i+1, seen + (nums(i) -> i))
      }

    move(0, Map.empty)
  }
}

class TwoSumSpec extends AnyFlatSpec {

  "twoSum" should "return [0,1] for nums=[2,7,11,15] and target=9" in {
    twoSum(Array(2, 7, 11, 15), 9) shouldEqual Array(0, 1)
  }

  it should "return [1,2] for nums=[3,2,4] and target=6" in {
    twoSum(Array(3, 2, 4), 6) shouldEqual Array(1, 2)
  }

  it should "return [0,1] for nums=[3,3] and target=6" in {
    twoSum(Array(3, 3), 6) shouldEqual Array(0, 1)
  }

  it should "return [0,1] for a two-element array where both sum to target" in {
    twoSum(Array(1, 9), 10) shouldEqual Array(0, 1)
  }

  it should "handle negative numbers" in {
    twoSum(Array(-3, 4, 3, 90), 0) shouldEqual Array(0, 2)
  }

  it should "handle target formed by a negative and positive number" in {
    twoSum(Array(-1, -2, -3, -4, -5), -8) shouldEqual Array(2, 4)
  }

}
