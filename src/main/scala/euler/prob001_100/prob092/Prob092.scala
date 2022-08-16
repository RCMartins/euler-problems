package euler.prob001_100.prob092

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob092 extends UtilResult {
  override def calc: Long = {
    val MAX = 1e7.toInt

    val SQUARES = (0 to 9).map(n => n * n).toVector

    def loop(n: Int): Boolean = {
      val sum = digits(n).map(digit => SQUARES(digit)).sum
      if (sum == 89)
        true
      else if (sum == 1)
        false
      else
        loop(sum)
    }

    (1 to MAX).count(loop)
  }
}
