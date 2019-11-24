package euler.prob001_100.prob006

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob006 extends UtilResult {

  def calc: Long = {
    val a = (1 to 100).map(n => n * n).sum
    val b = (1 to 100).sum

    b * b - a
  }

}
