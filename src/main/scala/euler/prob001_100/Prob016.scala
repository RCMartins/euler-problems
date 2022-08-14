package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob016 extends UtilResult {
  override def calc: Long = {
    val big = BigInt(2).pow(1000)
    big.toString.map(_ - '0').sum
  }
}
