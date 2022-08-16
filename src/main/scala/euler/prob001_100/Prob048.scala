package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob048 extends UtilResult {
  override def calc: Long = {
    val big = (1 to 1000).foldLeft(BigInt(0))((acc, n) => BigInt(n).pow(n) + acc)
    big.toString.takeRight(10).toLong
  }
}
