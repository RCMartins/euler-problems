package euler.prob301_400.prob357

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob357 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e8.toInt
    val all = ALL_PRIMES.takeWhile(_ < MAX).map(p => p - 1) //.map { n => println(n); n }

    def allFactorsFast(allFactors: List[(Long, Int)]): Stream[Long] = {
      allFactors match {
        case Nil => Stream(1)
        case (factor, amount) :: others =>
          Stream.from(1).take(amount).scanLeft(1L)((n, _) => n * factor).flatMap(n => allFactorsFast(others).map(_ * n))
      }
    }

    time {
      all.filter(n => allFactorsFast(factors(n)).forall(factor => testIsPrime(factor + n / factor))).sum
    }

  }

}
