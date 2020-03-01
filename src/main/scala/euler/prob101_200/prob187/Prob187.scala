package euler.prob101_200.prob187

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob187 extends Util {


  def main(args: Array[String]): Unit = {
    def testIsPrime(n: Int, primesSoFar: Vector[Int]): Boolean = {
      primesSoFar.foreach {
        prime =>
          if (prime.toLong * prime > n) {
            return true
          } else if (n % prime == 0) {
            return false
          }
      }
      false
    }

    def prime(n: Int, primesSoFar: Vector[Int]): Stream[Int] = {
      if (testIsPrime(n, primesSoFar)) {
        Stream.cons(n, prime(n + 1, primesSoFar :+ n))
      } else
        prime(n + 1, primesSoFar)
    }

    val t = System.currentTimeMillis()

    val MAX = 1e8.toLong
    val MAXPRIMES = MAX / 2

    val smallPrimes = 2 #:: prime(3, Vector(2))

    val primes = smallPrimes.takeWhile(_ < MAXPRIMES).force

    println("done in " + (System.currentTimeMillis() - t))

    //    println(primes.size)

    def loop(initialPrimes: Stream[Int], count: Set[Int]): Set[Int] = {
      def loopAux(currentPrime: Int, all: Stream[Int], count: Set[Int]): Set[Int] = {
        all match {
          case prime #:: others =>
            val newN: Long = currentPrime.toLong * prime
            if (newN < MAX)
              loopAux(currentPrime, others, count + newN.toInt)
            else
              count
          case _ => count
        }
      }

      initialPrimes match {
        case a #:: as => loop(as, loopAux(a, initialPrimes, count))
        case Stream() => count
      }
    }

    val set = loop(primes, Set())

    result(set.size)

    //17427258
  }

}
