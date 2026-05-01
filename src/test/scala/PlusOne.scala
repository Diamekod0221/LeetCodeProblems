import scala.+:
import scala.annotation.tailrec
//You are given a large integer represented as an integer array digits, where
// each digits[i] is the ith digit of the integer. The digits are ordered from most significant
// to least significant in left-to-right order. The large integer does not contain any leading 0's.
//
//Increment the large integer by one and return the resulting array of digits.
//
//
//
//Example 1:
//
//Input: digits = [1,2,3]
//Output: [1,2,4]
//Explanation: The array represents the integer 123.
//Incrementing by one gives 123 + 1 = 124.
//Thus, the result should be [1,2,4].
//Example 2:
//
//Input: digits = [4,3,2,1]
//Output: [4,3,2,2]
//Explanation: The array represents the integer 4321.
//Incrementing by one gives 4321 + 1 = 4322.
//Thus, the result should be [4,3,2,2].

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PlusOneSpec extends AnyFlatSpec with Matchers {

  def plusOne(digits: Array[Int]): Array[Int] = {
    add(digits.length - 1, digits)
  }

  @tailrec
  final def add(index: Int, digits: Array[Int]): Array[Int] = {
    if (index < 0) {
      1 +: digits
    }
    else {
      if (digits(index) == 9) {
        digits(index) = 0
        add(index - 1, digits)
      }
      else {
        digits.update(index, digits(index) + 1)
        digits
      }
    }
  }

  // Example 1
  "plusOne" should "increment [1,2,3] to [1,2,4]" in {
    plusOne(Array(1, 2, 3)) shouldEqual Array(1, 2, 4)
  }

  // Example 2
  it should "increment [4,3,2,1] to [4,3,2,2]" in {
    plusOne(Array(4, 3, 2, 1)) shouldEqual Array(4, 3, 2, 2)
  }

  // Carry propagation
  it should "handle carry when last digit is 9, e.g. [1,2,9] -> [1,3,0]" in {
    plusOne(Array(1, 2, 9)) shouldEqual Array(1, 3, 0)
  }

  it should "handle multiple carries, e.g. [1,9,9] -> [2,0,0]" in {
    plusOne(Array(1, 9, 9)) shouldEqual Array(2, 0, 0)
  }

  // All nines — requires expanding the array
  it should "expand array when all digits are 9, e.g. [9,9,9] -> [1,0,0,0]" in {
    plusOne(Array(9, 9, 9)) shouldEqual Array(1, 0, 0, 0)
  }

  it should "handle single digit 9 -> [1,0]" in {
    plusOne(Array(9)) shouldEqual Array(1, 0)
  }

  // Single digits
  it should "handle single digit 0 -> [1]" in {
    plusOne(Array(0)) shouldEqual Array(1)
  }

  it should "handle single digit other than 9, e.g. [5] -> [6]" in {
    plusOne(Array(5)) shouldEqual Array(6)
  }

  // Large number without carry
  it should "handle a large array with no carry, e.g. [1,0,0,0] -> [1,0,0,1]" in {
    plusOne(Array(1, 0, 0, 0)) shouldEqual Array(1, 0, 0, 1)
  }
}
