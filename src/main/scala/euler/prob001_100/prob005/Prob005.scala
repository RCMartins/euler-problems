package euler.prob001_100.prob005

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob005 extends UtilResult {

  def calc: Long = {
    val numsDiv = (3 to 19).toList
    val stream = NATURALS.map(_ * 20).filter(num => numsDiv.forall(div => num % div == 0))

    stream.head
  }

}
