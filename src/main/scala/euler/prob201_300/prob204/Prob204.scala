package euler.prob201_300.prob204

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob204 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e9.toLong
    val PRIMES_MAX = 100

    val factors = SMALL_PRIMES.takeWhile(_ <= PRIMES_MAX).map(_.toLong)
    println(factors.force)

    def calc(value: Long, primes: LazyList[Long]): LazyList[Long] = {
      primes match {
        case LazyList() => LazyList(value)
        case prime #:: others =>
          for {
            exp <- exponents(prime).takeWhile(_ < MAX)
            if value * exp <= MAX
            v <- calc(value * exp, others)
            if v <= MAX
          } yield v
      }
    }

    time(calc(1, factors).take(20).force)
    time(calc(1, factors).size)

    // 2944730

  }

}
