import scala.annotation.tailrec

class ArrayNesting {
  def arrayNesting(nums: Array[Int]): Int = {
    val visited = Array.fill(nums.length)(false)

    @tailrec
    def lengthK(current: Int, count: Int): Int = {
     if(visited(current)) count
     else{
       visited(current) = true
       lengthK(current +)
     }
    }

    nums.toList.map{ k => lengthK(nums(k) :: Nil, nums.toList, k)}.max
  }

}
