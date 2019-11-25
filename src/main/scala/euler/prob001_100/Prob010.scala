package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob010 extends UtilResult {
  def calc: Long = {
    ALL_PRIMES.takeWhile(_ < 2000000).sum
  }
}
