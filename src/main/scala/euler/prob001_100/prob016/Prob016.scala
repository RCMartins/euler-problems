package euler.prob001_100.prob016

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob016 extends UtilResult {
  def calc: Long = {
    val big = BigInt(2).pow(1000)
    big.toString.map(_ - '0').sum
  }
}
