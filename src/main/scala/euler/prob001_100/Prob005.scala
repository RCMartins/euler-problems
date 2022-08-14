package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob005 extends UtilResult {
  override def calc: Long = {
    val numsDiv = (3 to 19).toList
    val LazyList = NATURALS.map(_ * 20).filter(num => numsDiv.forall(div => num % div == 0))

    LazyList.head
  }
}
