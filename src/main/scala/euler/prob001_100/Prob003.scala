package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob003 extends UtilResult {
  def calc: Long = {
    val NUMBER = 600851475143L
    val sqrtNum = BigInt(NUMBER).sqrt().toLong

    ALL_PRIMES.takeWhile(_ < sqrtNum).toList.filter(NUMBER % _ == 0).max
  }
}
