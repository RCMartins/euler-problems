package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob029 extends UtilResult {
  override def calc: Long = {
    val range = 2 to 100

    val list = for {
      a <- range
      b <- range
    } yield BigInt(a).pow(b)

    list.distinct.size
  }
}
