package euler.prob001_100.prob087

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob087 extends UtilResult {
  override def calc: Long = {
    val MAX = 5e7

    val v = for {
      p4 <- ALL_PRIMES.map(p => p * p * p * p).takeWhile(_ < MAX)
      p3 <- ALL_PRIMES.map(p => p * p * p).takeWhile(_ < MAX)
      p2 <- ALL_PRIMES.map(p => p * p).takeWhile(_ < MAX)
      n = p4 + p3 + p2
      if n <= MAX
    } yield n

    v.toSet.size
  }
}
