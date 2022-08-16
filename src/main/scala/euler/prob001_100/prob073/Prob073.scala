package euler.prob001_100.prob073

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob073 extends UtilResult {
  override def calc: Long = {
    val MAX = 12000

    val L = 1.0 / 3
    val H = 1.0 / 2

    var count = 0
    for {
      a <- 1 to MAX
      b <- a + 1 to MAX
      if new RacValue(a, b).isReducedProperFraction
      c = a.toDouble / b
      if c > L && c < H
    } count += 1

    count
  }
}
