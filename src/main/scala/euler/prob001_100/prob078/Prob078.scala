package euler.prob001_100.prob078

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob078 extends UtilResult {
  def calc: Long = {
    val MaxMoney = 500

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

    for (x <- 0 to MaxMoney) {
      for (y <- 0 until MaxMoney) {
        if (data(x)(y) % 1000000 == 0)
          result((x, data(x)(y)))
      }
    }

//    result(data(MAX_MONEIES)(MAX_MONEIES - 1))

    ???
  }
}
