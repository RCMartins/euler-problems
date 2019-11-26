package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob035 extends UtilResult {
  def calc: Long = {
    val MAX = 1000000

    val v = for {
      n <- ALL_PRIMES.takeWhile(_ < MAX)
      nStr = n.toString
      if nStr.length == 1 || (1 until nStr.length)
        .forall(n => testIsPrime((nStr.drop(n) + nStr.take(n)).toLong))
    } yield n

    v.size
  }
}
