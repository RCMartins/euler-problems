package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob002 extends UtilResult {
  override def calc: Long = {
    ALL_FIB.takeWhile(_ < 4000000).filter(_ % 2 == 0).sum
  }
}
