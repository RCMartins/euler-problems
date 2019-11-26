package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob027 extends UtilResult {
  def calc: Long = {
    // n^2 + an + b

    // inferred: b has to be positive ?

    var maxN = 0
    var ab = 0
    var fa, fb = 0

    for {
      b <- SMALL_PRIMES.takeWhile(_ < 1000)
      a <- -999 to 999
    } {
      var isPrime = true
      var n = 0
      while (isPrime) {
        n += 1
        val posPrime = n * n + a * n + b
        isPrime = posPrime > 0 && testIsPrime(posPrime)
      }
      if (n > maxN) {
        maxN = n
        ab = a * b
        fa = a
        fb = b
      }
    }

    ab
  }
}
