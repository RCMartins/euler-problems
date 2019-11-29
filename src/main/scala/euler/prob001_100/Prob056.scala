package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob056 extends UtilResult {
  def calc: Long = {
    val v = for {
      a <- 1 to 100
      b <- 1 to 100
    } yield BigInt(a).pow(b)

    v.map(_.toString.map(_ - '0').sum).max
  }
}
