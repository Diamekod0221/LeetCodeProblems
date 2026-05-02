import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec

class ValidParenthesesSpec extends AnyFlatSpec with Matchers {
  val brackets: Set[(Char, Char)] = Set(
    ('(', ')'),
    ('[', ']'),
    ('{', '}')
  )

  val opening: Set[Char] = brackets.map(_._1)
  val closing: Set[Char] = brackets.map(_._2)

  def findClosing(bracket: Char): Option[Char] = brackets.find(_._1 == bracket).map(_._2)

  def isValid(s: String): Boolean = {
    @tailrec
    def loop(remaining: List[Char], acc: List[Char]): Boolean ={
      remaining match {
        case Nil => acc.isEmpty
        case c :: rest if opening.contains(c) => loop(rest, c :: acc)
        case c :: rest if closing.contains(c) =>
          acc match {
            case top :: stackRest if (findClosing(top).contains(c)) => loop(rest, stackRest)
            case _ => false
          }
        case _ :: rest => loop(rest, acc)
      }}
    loop(s.toList, Nil)
  }

  "isValid" should "return true for valid" in {
    isValid("(123)") shouldBe true
  }

  it should "handle a weird ass parentheses" in {
    isValid("([)]") shouldBe false
  }
}

