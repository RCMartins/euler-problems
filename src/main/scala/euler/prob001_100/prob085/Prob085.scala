package euler.prob001_100.prob085

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob085 extends UtilResult {
  override def calc: Long = {
    time {
      val max = 100

      val result =
        for {
          mx <- 1 to max
          my <- 1 to max

          sum = {
            def count(sx: Int, sy: Int): Long = {
              (mx - sx + 1) * (my - sy + 1)
            }

            val v =
              for {
                sx <- 1 to mx
                sy <- 1 to my
              } yield count(sx, sy)

            v.sum
          }
        } yield (mx, my, sum)

      result
        .map { case (x, y, rec) => (x * y, Math.abs(rec - 2000000)) }
        .minBy(_._2)
        ._1
    }
  }
}
