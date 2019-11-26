package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob031 extends UtilResult {
  def calc: Long = {
    val coins = Vector(1, 2, 5, 10, 20, 50, 100, 200)
    val length = coins.length
    val initialMoney = 200

    val data: Array[Array[Int]] = Array.ofDim(initialMoney + 1, length)
    for (y <- 0 until length) data(0)(y) = 1
    for (x <- 1 to initialMoney) data(x)(0) = 1

    def get(x: Int, y: Int) = if (x < 0 || y < 0) 0 else data(x)(y)

    for {
      money <- 1 to initialMoney
      coinI <- 1 until length
      coinV = coins(coinI)
    } {
      if (coinV <= money)
        data(money)(coinI) = get(money - coinV, coinI) + get(money, coinI - 1)
      else
        data(money)(coinI) = get(money, coinI - 1)
    }

    data(initialMoney)(coins.length - 1)
  }
}
