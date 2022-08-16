package euler.prob001_100.prob076

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob076 extends UtilResult {
  override def calc: Long = {
    val MaxMoney = 100

    val data: Array[Array[Long]] = Array.ofDim(MaxMoney + 1, MaxMoney)
    for (y <- 0 until MaxMoney) data(0)(y) = 1
    for (x <- 1 to MaxMoney) data(x)(0) = 1

    def get(x: Int, y: Int) = if (x < 0 || y < 0) 0L else data(x)(y)

    for {
      money <- 1 to MaxMoney
      coinI <- 1 until MaxMoney
      coinV = coinI + 1
    } {
      if (coinV <= money)
        data(money)(coinI) = get(money - coinV, coinI) + get(money, coinI - 1)
      else
        data(money)(coinI) = get(money, coinI - 1)
    }

    data(MaxMoney)(MaxMoney - 1) - 1
  }
}
