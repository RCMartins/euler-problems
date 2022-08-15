package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob078 extends UtilResult {
  def calc: Long = {
    val MaxMoney = 20

    val data: Array[Array[Long]] = Array.ofDim(MaxMoney + 1, MaxMoney)
    for (y <- 0 until MaxMoney) data(0)(y) = 1
    for (x <- 1 to MaxMoney) data(x)(0) = 1

    @inline def get(x: Int, y: Int): Long =
      if (x < 0 || y < 0)
        0L
      else {
        println((x, y, data(x)(y)))
        data(x)(y)
      }

    for {
      money <- 1 to MaxMoney
      coinI <- 1 until MaxMoney
      coinV = coinI + 1
    } {
      if (coinV <= money)
        data(money)(coinI) = (get(money - coinV, coinI) + get(money, coinI - 1)) % 1000000
      else
        data(money)(coinI) = get(money, coinI - 1)
    }

    for (x <- 1 to MaxMoney) {
      val y = x - 1
      if (x < 15)
        println(s"$x: ${data(x)(y)}")
      if (data(x)(y) % 1000000 == 0)
        result((x, y, data(x)(y)))
    }

//    println()

    data(MaxMoney)(MaxMoney - 1)

    // wrong: 441
  }
}
