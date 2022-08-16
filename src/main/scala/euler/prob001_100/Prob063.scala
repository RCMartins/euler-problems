package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob063 extends UtilResult {
  override def calc: Long = {
    val v = for {
      n <- 1 to 1000
      base <- 1 to 100
      if BigInt(base).pow(n).toString.length == n
    } yield (n, BigInt(base).pow(n))

    v.map(_._2).toSet.size
  }
}
