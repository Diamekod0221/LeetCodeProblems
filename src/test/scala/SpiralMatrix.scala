import scala.annotation.tailrec

sealed trait Direction
  case object Up extends Direction
  case object Down extends Direction
  case object Left extends Direction
  case object Right extends Direction

object Solution {
  implicit class TupleOps(t: (Int, Int)) {
    def +(other: (Int, Int)): (Int, Int) = (t._1 + other._1, t._2 + other._2)
  }
  def spiralMatrixIII(rows: Int, cols: Int, rStart: Int, cStart: Int): Array[Array[Int]] = {

    @tailrec
    def step(previousSteps:List[(Int,Int)], direction: Direction, rows: Int, cols: Int): List[(Int,Int)]={
      if(previousSteps.length== rows*cols){return previousSteps}

      val nextStep = direction match {
        case Up => previousSteps.head + (-1,0)
        case Down => previousSteps.head + (1,0)
        case Left => previousSteps.head + (0,-1)
        case Right => previousSteps.head + (0,1)
      }

      if ((0 <= nextStep._1 && nextStep._1 <= rows - 1) && (0 <= nextStep._2 && nextStep._2 <= cols - 1)){
        step(nextStep :: previousSteps, direction = direction, rows, cols)
      }
      else {

      }

    }
  }
}
