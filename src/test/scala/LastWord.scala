import LastWord.lengthOfLastWord
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
///Given a string s consisting of words and spaces, return the length of the last word in the string.
//
//A word is a maximal substring consisting of non-space characters only.
//
//
//
//Example 1:
//
//Input: s = "Hello World"
//Output: 5
//Explanation: The last word is "World" with length 5.
//Example 2:
//
//Input: s = "   fly me   to   the moon  "
//Output: 4
//Explanation: The last word is "moon" with length 4.
//Example 3:
//
//Input: s = "luffy is still joyboy"
//Output: 6
//Explanation: The last word is "joyboy" with length 6.

object LastWord {
  def lengthOfLastWord(s: String): Int = {
    val end = s.lastIndexWhere(_ != ' ')
    if (end < 0) 0
    else end - s.lastIndexOf(' ', end)
  }
}

class LastWordSpec extends AnyFlatSpec {
  "lastWord" should "handle trailing space properly" in {
    lengthOfLastWord("asd  asd  ") shouldBe 3
  }
}
