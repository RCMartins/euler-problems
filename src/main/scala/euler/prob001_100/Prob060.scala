package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob060 extends UtilResult {
  def calc: Long = {
    @inline def join(l1: Long, l2: Long) = (l1.toString + l2.toString).toLong

    def testAllCombPrimes(list: List[Long]): Boolean = {
      list match {
        case Nil | List(_) => true
        case prime :: others =>
          others.forall(
            prime2 => testIsPrime(join(prime, prime2)) && testIsPrime(join(prime2, prime))
          ) &&
            testAllCombPrimes(others)
      }
    }

    val primes = ALL_PRIMES.takeWhile(_ <= 10000)
    val v = for {
      comb2Primes <- primes.combinations(2).map(_.toList)
      if testAllCombPrimes(comb2Primes)
      thirdPrime <- primes.filter(
        prime => !comb2Primes.contains(prime) && testAllCombPrimes(prime :: comb2Primes)
      )
      comb3Primes = thirdPrime :: comb2Primes
      fourthPrime <- primes.filter(
        prime => !comb3Primes.contains(prime) && testAllCombPrimes(prime :: comb3Primes)
      )
      comb4Primes = fourthPrime :: comb3Primes
      fifthPrime <- primes.filter(
        prime => !comb4Primes.contains(prime) && testAllCombPrimes(prime :: comb4Primes)
      )
      comb5Primes = fifthPrime :: comb4Primes
    } yield {
      comb5Primes
    }

    v.map(_.sum).min
  }
}
