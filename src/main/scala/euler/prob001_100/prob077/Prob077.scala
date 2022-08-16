package euler.prob001_100.prob077

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob077 extends UtilResult {
  override def calc: Long = {
    val coins = ALL_PRIMES.take(200).map(_.toInt).toVector
    val length = coins.length
    val initialMoney = 100

    val data: Array[Array[Int]] = Array.ofDim(initialMoney + 1, length)
    for (y <- 0 until length) data(0)(y) = 1
    for (x <- 1 to initialMoney) data(x)(0) = if (x % 2 == 0) 1 else 0

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

      if (data(money)(coinI) >= 5000) {
        return money
      }
    }

    ???
  }
}
