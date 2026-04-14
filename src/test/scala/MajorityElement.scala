class MajorityElement {
  def majorityElement(nums: Array[Int]): Int = {
    var occurences = Map.empty[Int, Int]
    for ( i <- nums.indices) {
      val checked = occurences.getOrElse(nums(i), 0) + 1
      if(checked > Math.floor(nums.length/2)) return nums(i)
      occurences += nums(i) -> checked
    }
    if(occurences.nonEmpty) occurences.max._1
    else 0
  }

}
