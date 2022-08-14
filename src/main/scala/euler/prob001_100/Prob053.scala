package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob053 extends UtilResult {
  override def calc: Long = {
    def factorial(n: Int) = (2 to n).foldLeft(BigInt(1))((b, v) => b * v)

    def comb(n: Int, r: Int): BigInt = factorial(n) / (factorial(r) * factorial(n - r))

    val v = for {
      n <- 1 to 100
      r <- 1 to 100
      if comb(n, r) > 1000000
    } yield (n, r)

    v.size
  }
}
