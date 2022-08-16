package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob049 extends UtilResult {
  override def calc: Long = {
    val primes = ALL_PRIMES.filter(_ >= 1000).takeWhile(_ <= 9999)
    val primesSet = primes.toSet

    val v = for {
      a <- primes
      b <- primes
      if a.toString.sorted == b.toString.sorted
      c = b * 2 - a
      if b > a && primesSet(c) && a.toString.sorted == c.toString.sorted
    } yield a.toString + b.toString + c.toString

    v.filter(!_.startsWith("1487")).head.toLong
  }
}
