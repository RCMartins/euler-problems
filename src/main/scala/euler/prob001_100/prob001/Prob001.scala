package euler.prob001_100.prob001

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob001 extends UtilResult {

  def calc: Long = {
    (1 until 1000).filter(n => n % 3 == 0 || n % 5 == 0).sum
  }

}
