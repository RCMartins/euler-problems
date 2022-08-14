package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob041 extends UtilResult {
  override def calc: Long = {
    val v = for {
      maxN <- 1 to 9
      list = (1 to maxN).permutations.toList
      numberList <- list
      number = numberList.mkString.toInt
      if testIsPrime(number, ALL_PRIMES)
    } yield number

    v.last
  }
}
