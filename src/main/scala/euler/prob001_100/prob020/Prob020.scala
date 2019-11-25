package euler.prob001_100.prob020

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob020 extends UtilResult {
  def calc: Long = {
    val big = (2 to 100).map(BigInt.apply).product
    result(big)
    big.sumDigits
  }
}
