package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob020 extends UtilResult {
  override def calc: Long = {
    val big = (2 to 100).map(BigInt.apply).product
    big.sumDigits
  }
}
