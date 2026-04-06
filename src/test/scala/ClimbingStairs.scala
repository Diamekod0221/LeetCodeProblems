import ClimbingFibonacci.climbStairs
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
///70. Climbing Stairs
//You are climbing a staircase. It takes n steps to reach the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//Example 1:
//
//Input: n = 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
//Example 2:
//
//Input: n = 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
//
//
//Constraints:
//
//1 <= n <= 45

object ClimbingStairs {
  def climbStairs(n: Int): Int = (0 to n/2).map(twos => permsForPair(n - 2*twos, twos)).sum.toInt

  def permsForPair(ones: Int, twos: Int): BigInt = {
    val n = ones + twos
    (1 to n).map(BigInt(_)).product / ((1 to ones).map(BigInt(_)).product * (1 to twos).map(BigInt(_)).product)
  }
}

object ClimbingFibonacci {
  def climbStairs(n: Int): Int = {
    n match {
      case 0 => 1
      case 1 =>  1
      case 2 =>  2
      case _ => climbStairs(n-1) + climbStairs(n-2)
    }
  }
}

class ClimbingStairsTest extends AnyFlatSpec {
  "climbStairs" should "return 1 way to climb 1 step" in {
    climbStairs(1) shouldBe 1
  }

  it should "return 2 ways to climb 2 steps" in {
    climbStairs(2) shouldBe 2
  }

  it should "return 3 ways to climb 3 steps" in {
    climbStairs(3) shouldBe 3
  }

  it should "return 5 ways to climb 4 steps" in {
    climbStairs(4) shouldBe 5
  }

  it should "return 8 ways to climb 5 steps" in {
    climbStairs(5) shouldBe 8
  }

  it should "handle n=35" in {
    climbStairs(35)
  }

  it should "handle larger inputs" in {
    climbStairs(10) shouldBe 89
    climbStairs(20) shouldBe 10946
  }

  it should "return 1 for 0 steps (base case)" in {
    climbStairs(0) shouldBe 1
  }
}
