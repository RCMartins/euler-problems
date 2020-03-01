package euler.prob101_200.prob123

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob123 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e10.toLong

    val primes = ALL_PRIMES

    val v = for {
      (prime, index) <- primes.zip(Stream.from(1))
      prime2 = prime * prime
      if (BigInt(prime - 1).modPow(index, prime2) + BigInt(prime + 1).modPow(index, prime2)).mod(prime2) > MAX
    } yield index

    result(v.head)
  }

}
