package euler.prob401_500

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob493 extends UtilResult {

  def calc: Long = {
    val totalBalls = 20
    val totalColors = 7
    val totalBallsByColor = 10
    val total = totalColors * totalBallsByColor

    val samples: BigInt =
      (total to 0 by -1).take(totalBalls).foldLeft(BigInt(1))((acc, n) => acc * n)

    def exactly(n: Int): BigInt = {
      def loop(r: Int, ballsTaken: Int): BigInt =
        if (ballsTaken == totalBalls)
          if (r == n) BigInt(1) else BigInt(0)
        else if (r == n)
          BigInt(n * 10 - ballsTaken) * loop(r, ballsTaken + 1)
        else
          BigInt(total - r * 10) * loop(r + 1, ballsTaken + 1) + {
            val currentBallColors = r * 10 - ballsTaken
            if (currentBallColors > 0)
              BigInt(currentBallColors) * loop(r, ballsTaken + 1)
            else
              BigInt(0)
          }

      BigInt(n) * BigInt(70) * loop(r = 1, ballsTaken = 1)
    }

    val finalResult: BigInt =
      (2 to 7).map(exactly).foldLeft(BigInt(1))(_ + _)

    (BigDecimal(finalResult) / BigDecimal(samples)).toString.take(11).replace(".", "").toLong
  }

}
