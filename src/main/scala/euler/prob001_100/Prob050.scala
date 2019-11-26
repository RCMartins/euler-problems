package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob050 extends UtilResult {
  def calc: Long = {
    val MAX = 1000000

    val primes = ALL_PRIMES.takeWhile(_ < MAX).map(_.toInt).toList
    val isPrime = primes.toSet

    var maxSeq = 0
    var maxSeqNumber = 0

    def biggestSeq(prime: Int, otherPrimes: List[Int], acc: Int, seqSize: Int): Unit = {
      val nAcc = acc + prime
      if (nAcc < MAX) {
        if (isPrime(nAcc) && seqSize > maxSeq) {
          maxSeq = seqSize + 1
          maxSeqNumber = nAcc
        }
        otherPrimes match {
          case Nil     =>
          case x :: xs => biggestSeq(x, xs, nAcc, seqSize + 1)
        }
      }
    }

    def calcAllSeqs(primes: List[Int]): Unit = {
      primes match {
        case Nil =>
        case prime :: others =>
          biggestSeq(prime, others, 0, 0)
          calcAllSeqs(others)
      }
    }

    calcAllSeqs(primes)

//    println(maxSeq, maxSeqNumber)

    maxSeqNumber
  }
}
