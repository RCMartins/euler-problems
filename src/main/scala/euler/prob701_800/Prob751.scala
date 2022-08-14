package euler.prob701_800

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob751 extends UtilResult {

  override def calcBigDecimal: BigDecimal = {
    def calcWith(theta: BigDecimal): LazyList[Int] = {
      def calcNext(big: BigDecimal): LazyList[BigDecimal] = {
        val floor = big.intValue
        val result = (big + 1 - floor) * floor
        result #:: calcNext(result)
      }

      val bSeq: LazyList[BigDecimal] = theta #:: calcNext(theta)
      val aSeq: LazyList[Int] = bSeq.map(_.intValue)
      aSeq
    }

    @tailrec
    def getBestUntil(theta: BigDecimal, nDigits: Int): String = {
      val result = calcWith(theta)
      val resultStr = result.slice(1, Math.min(nDigits + 1, 15)).mkString.take(nDigits)
      val sameChars: String =
        theta.toString
          .drop(2)
          .zip(resultStr)
          .takeWhile { case (c1, c2) => c1 == c2 }
          .map(_._1)
          .mkString
      if (sameChars.length >= nDigits)
        sameChars
      else
        getBestUntil(BigDecimal(s"2.$resultStr"), nDigits)
    }

    BigDecimal("2." + getBestUntil(BigDecimal(2.2), 24))

    // answer: 2.223561019313554106173177
  }
}
