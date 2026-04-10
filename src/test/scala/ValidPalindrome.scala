import ValidPalindrome.isPalindrome
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

object ValidPalindrome {
  //
  //Given a string s, return true if it is a palindrome, or false otherwise.
  //
  //
  //
  //Example 1:
  //
  //Input: s = "A man, a plan, a canal: Panama"
  //Output: true
  //Explanation: "amanaplanacanalpanama" is a palindrome.
  //Example 2:
  //
  //Input: s = "race a car"
  //Output: false
  //Explanation: "raceacar" is not a palindrome.
  //Example 3:
  //
  //Input: s = " "
  //Output: true
  //Explanation: s is an empty string "" after removing non-alphanumeric characters.
  //Since an empty string reads the same forward and backward, it is a palindrome.
  def isPalindrome(s: String): Boolean = {
    val replaced = s.replaceAll("[^a-zA-Z0-9]+", "").trim.toLowerCase
    replaced == replaced.reverse
  }
}

class ValidPalindromeTest extends AnyFlatSpec{
  "isPalindrome" should "return true for valid palindrome" in {
    val input = "A man, a plan, a canal: Panama"
    isPalindrome(input) shouldBe true
  }

  it should "return false for an invalid palindrome" in {
    val input = "race a car"
    isPalindrome(input) shouldBe false
  }

  it should "handle empty strings" in {
    val input = "    "
    isPalindrome(input) shouldBe true
  }

  it should "handle string of non alphanumeric" in {
    val input = ";_ -  "
    isPalindrome(input) shouldBe true
  }
}
