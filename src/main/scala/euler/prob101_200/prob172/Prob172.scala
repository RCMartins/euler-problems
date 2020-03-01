package euler.prob101_200.prob172

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob172 extends Util {

  def main(args: Array[String]): Unit = {

    val Pos = (0 to 9).map(d => Math.pow(2, d * 2).toInt).toArray
    //    println(Pos.mkString("\n"))

    val MaxCode = 2 << 19
    //    println(MaxCode)

    def filterAmount(code: Int, d: Int) = {
      (code >> d * 2) & 0x3
    }

    time {
      val BIG_ARRAY = Array.ofDim[Long](18, 2 << 20)

      for {
        d <- 0 until 18
        code <- 0 until MaxCode
      } BIG_ARRAY(d)(code) = -1

      def loop(size: Int, code: Int): Long = {
        def aux(size: Int, code: Int): Long = {
          if (BIG_ARRAY(size)(code) == -1) {
            BIG_ARRAY(size)(code) =
              if (size == 0)
                1L
              else {
                var total = 0L
                for (n <- 0 to 9)
                  if (filterAmount(code, n) > 0)
                    total += aux(size - 1, code - Pos(n))
                total
              }
          }
          BIG_ARRAY(size)(code)
        }

        var total = 0L
        for (n <- 1 to 9)
          if (filterAmount(code, n) > 0)
            total += aux(size - 1, code - Pos(n))
        total
      }

      loop(18, MaxCode - 1)
    }
  }

}
