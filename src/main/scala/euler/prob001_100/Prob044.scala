package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob044 extends UtilResult {
  def calc: Long = {
    def pentagonStream(n: Long): Stream[Long] =
      Stream.cons(n * (3 * n - 1) / 2, pentagonStream(n + 1))

    val ALL_PENTAGON = pentagonStream(1L)
    val SMALL_PENTAGON = ALL_PENTAGON.takeWhile(_ < 10000000)
    val PENTAGONSET = SMALL_PENTAGON.toSet

    val v = for {
      a <- SMALL_PENTAGON
      b <- SMALL_PENTAGON
      if a < b && PENTAGONSET(math.abs(a - b)) && PENTAGONSET(a + b)
    } yield math.abs(a - b)

    v.min
  }
}
