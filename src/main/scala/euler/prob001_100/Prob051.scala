package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob051 extends UtilResult {
  def calc: Long = {
    val v = for {
      prime <- ALL_PRIMES.drop(4)
      primeStr = prime.toString
      combSize <- 1 until primeStr.length
      comb <- (0 to primeStr.length - 2).combinations(combSize)
      replacedPrimes = (0 to 9).map(digit => {
        val numberStr =
          comb.foldLeft(primeStr)((str, index) => str.updated(index, digit).mkString)
        (numberStr, !numberStr.startsWith("0") && testIsPrime(numberStr.toLong))
      })
      if 8 == replacedPrimes.count(_._2)
    } yield {
      replacedPrimes.find(_._2).get._1.toLong
    }

    v.head
  }
}
