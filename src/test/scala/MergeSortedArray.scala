import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable.ArrayBuffer
//88. Merge Sorted Array
//You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
// representing the number of elements in nums1 and nums2 respectively.
//
//Merge nums1 and nums2 into a single array sorted in non-decreasing order.
//
//The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
// To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
// and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

object MergeSortedArray {
  def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
    val numsBuff = nums1.take(m).to[ArrayBuffer]
    var counter = 0

    for (i <- 0 until m) {
      var range = counter
      while (range < n && nums2(range) < nums1(i)) {
        range += 1
      }
      val sub = nums2.slice(counter, range)
      numsBuff.insertAll(i + counter, sub)
      counter = range
    }

    // insert any remaining nums2 elements
    numsBuff.appendAll(nums2.slice(counter, n))

    numsBuff.copyToArray(nums1)
  }
}

class MergeSortedArraySpec extends AnyFlatSpec {
  import MergeSortedArray.merge

  "merge" should "handle the first example: [1,2,3] merged with [2,5,6]" in {
    val nums1 = Array(1, 2, 3, 0, 0, 0)
    merge(nums1, 3, Array(2, 5, 6), 3)
    nums1 shouldEqual Array(1, 2, 2, 3, 5, 6)
  }

  it should "handle the second example: [1] merged with []" in {
    val nums1 = Array(1)
    merge(nums1, 1, Array(), 0)
    nums1 shouldEqual Array(1)
  }

  it should "handle the third example: [] merged with [1]" in {
    val nums1 = Array(0)
    merge(nums1, 0, Array(1), 1)
    nums1 shouldEqual Array(1)
  }

  // --- Edge cases ---

  it should "handle both arrays empty" in {
    val nums1 = Array.emptyIntArray
    merge(nums1, 0, Array.emptyIntArray, 0)
    nums1 shouldEqual Array.emptyIntArray
  }

  it should "handle nums2 entirely before nums1" in {
    val nums1 = Array(5, 6, 7, 0, 0, 0)
    merge(nums1, 3, Array(1, 2, 3), 3)
    nums1 shouldEqual Array(1, 2, 3, 5, 6, 7)
  }

  it should "handle nums2 entirely after nums1" in {
    val nums1 = Array(1, 2, 3, 0, 0, 0)
    merge(nums1, 3, Array(5, 6, 7), 3)
    nums1 shouldEqual Array(1, 2, 3, 5, 6, 7)
  }

  it should "handle all duplicate values" in {
    val nums1 = Array(2, 2, 2, 0, 0, 0)
    merge(nums1, 3, Array(2, 2, 2), 3)
    nums1 shouldEqual Array(2, 2, 2, 2, 2, 2)
  }

  it should "handle negative numbers" in {
    val nums1 = Array(-5, -3, -1, 0, 0, 0)
    merge(nums1, 3, Array(-4, -2, 0), 3)
    nums1 shouldEqual Array(-5, -4, -3, -2, -1, 0)
  }

  it should "handle mixed negative and positive numbers" in {
    val nums1 = Array(-3, 0, 5, 0, 0)
    merge(nums1, 3, Array(-1, 4), 2)
    nums1 shouldEqual Array(-3, -1, 0, 4, 5)
  }

  it should "handle single element arrays that swap" in {
    val nums1 = Array(2, 0)
    merge(nums1, 1, Array(1), 1)
    nums1 shouldEqual Array(1, 2)
  }

  it should "handle nums1 with one element and nums2 with many" in {
    val nums1 = Array(3, 0, 0, 0, 0)
    merge(nums1, 1, Array(1, 2, 4, 5), 4)
    nums1 shouldEqual Array(1, 2, 3, 4, 5)
  }

  it should "handle large values near constraint boundary" in {
    val nums1 = Array(-1000000000, 0, 1000000000, 0, 0)
    merge(nums1, 3, Array(-999999999, 999999999), 2)
    nums1 shouldEqual Array(-1000000000, -999999999, 0, 999999999, 1000000000)
  }
}
