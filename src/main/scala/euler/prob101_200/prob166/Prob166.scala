package euler.prob101_200.prob166

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob166 extends Util {

  @inline
  private final def v(i: Int): Boolean = i >= 0 && i <= 9

  def main(args: Array[String]): Unit = {

    /**
      * 00 01 02 03
      * 04 05 06 07
      * 08 09 10 11
      * 12 13 14 15
      */

    time {
      var count = 0

      for {
        d0 <- 0 to 9
        d1 <- 0 to 9
        d2 <- 0 to 9
        d3 <- 0 to 9

        row = d0 + d1 + d2 + d3

        d4 <- 0 to 9
        d5 <- 0 to 9
        d6 <- 0 to 9
        d7 = row - (d4 + d5 + d6) if v(d7)

        if row == d4 + d5 + d6 + d7
        if d0 + d4 <= row && d1 + d5 <= row && d2 + d6 <= row && d3 + d7 <= row
        if d0 + d5 <= row && d3 + d6 <= row

        d8 <- 0 to 9 if d0 + d4 + d8 <= row
        d9 <- 0 to 9 if d1 + d5 + d9 <= row && d3 + d6 + d9 <= row
        d10 <- 0 to 9 if d2 + d6 + d10 <= row && d0 + d5 + d10 <= row

        d11 = row - (d8 + d9 + d10) if v(d11)
        d12 = row - (d0 + d4 + d8) if v(d12)
        d13 = row - (d1 + d5 + d9) if v(d13)
        d14 = row - (d2 + d6 + d10) if v(d14)
        d15 = row - (d3 + d7 + d11) if v(d15)

        if d12 + d13 + d14 + d15 == row

        if d0 + d5 + d10 + d15 == row && d3 + d6 + d9 + d12 == row
      } {
        count += 1
      }

      count
    }

    // 7130034

  }

}
