package euler.prob001_100.prob097

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob097 extends UtilResult {
  def calc: Long = {
    val pow2 = BigInt(2).modPow(7830457, BigInt(10000000000L))
    val prime = pow2 * 28433 + 1
    prime.toString.takeRight(10).toLong
  }
}
