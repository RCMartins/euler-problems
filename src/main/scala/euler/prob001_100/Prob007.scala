package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob007 extends UtilResult {
  override def calc: Long = {
    ALL_PRIMES.drop(10000).head
  }
}
