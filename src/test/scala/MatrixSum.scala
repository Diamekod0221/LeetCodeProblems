import matrixSum.{diagonalSum, functionalSum}
import org.scalatest.funsuite.AnyFunSuite
///Given a square matrix mat, return the sum of the matrix diagonals.
//
//Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal
// that are not part of the primary diagonal.

object matrixSum{
  def diagonalSum(mat: Array[Array[Int]]): Int = {

    var sum = 0
    for {i <- mat.indices} {
      val row = mat(i)
      val rowSum = row(i) + row(mat.length - (i+1))
      sum += rowSum
      println(sum)
    }
    mat.length match {
      case even if (even % 2 == 0) => sum
      case odd => {
        val middleIndex = odd match {
          case 1 =>  0
          case _ =>  (mat.length / 2).floor.toInt
        }
        println(middleIndex)
        sum -= mat(middleIndex)(middleIndex)
        sum
      }

    }
  }

  def functionalSum(mat: Array[Array[Int]]): Int = {
    val n = mat.length
    val primaryDiagonalSum = (0 until n).map(i => mat(i)(i)).sum
    val secondaryDiagonalSum = (0 until n).map(i => mat(i)(n - 1 - i)).sum
    val overlap = if (n % 2 ==1){mat(n/2)(n/2)} else 0
    println(primaryDiagonalSum, secondaryDiagonalSum, overlap)
    primaryDiagonalSum + secondaryDiagonalSum - overlap
  }
}

class MatrixSumTest extends AnyFunSuite {
  val basicCase = Array(Array(1,2,3),Array(4,5,6),Array(7,8,9))
  test("basic case"){
    assert(diagonalSum(basicCase) == 25)
  }

  test("functional basic"){
    assert(functionalSum(basicCase) == 25)
  }
}
