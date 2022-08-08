package euler.prob401_500

import euler.traits.UtilResult

import scala.util.chaining.scalaUtilChainingOps

/** Created by Ricardo
  */
object Prob429 extends UtilResult {

  def calc: Long = {

    // S(2!)  = 5              1, 2
    // S(3!)  = 50             1, 2,  3,  6
    // S(4!)  = 650            1, 3,  8, 24
    // S(5!)  = 16900          1, 3,  5,  8,  15,  24,  40,  120
    // S(6!)  = 547924         1, 5,  9, 16,  45,  80, 144,  720
    // S(7!)  = 27396200       1, 5,  7,  9,  16,  35,  45,   63,   80,  112,   144,   315,   560,    720,   1008,    5040
    // S(8!)  = 746640991      1, 5,  7,  9,  35,  45,  63,  128,  315,  640,   896,  1152,  4480,   5760,   8064,   40320
    // S(9!)  = 773879749      1, 5,  7, 35,  81, 128, 405,  567,  640,  896,  2835,  4480, 10368,  51840,  72576,  362880
    // S(10!) = 683631060      1, 7, 25, 81, 175, 256, 567, 1792, 2025, 6400, 14175, 20736, 44800, 145152, 518400, 3628800
    // S(11!) = 402988573      1, 7, 11, 25,  77,  81, 175,  256,  275,  567,   891,  1792,  1925,   2025,   2816,    6237, 6400, 14175, 19712, 20736, 22275, 44800, 70400, 145152, 155925, 228096, 492800, 518400, 1596672, 3628800, 5702400, 39916800
    // S(12!) = 478433134
    // S(13!) = 13! > Int

    val target = 3
    val module = 1000000009L

    def bruteForce(): Long = {
      val n = BigInt(target).factorial
      val nInt = n.toInt
      val nLong = n.toLong
      if (nInt != nLong)
        -1
      else
        (1 to nInt)
          .filter {
            case d =>
              val div = nLong / d
              div * d == nLong && GCD(d, div) == 1
            case _ =>
              false
          }
          .tap(vec => println(vec.mkString(", ")))
          .foldLeft(0L)((acc, d) => (acc + (d.toLong * d)) % module)
    }
    bruteForce().tap(println(_))

    val primes =
      SMALL_PRIMES.takeWhile(_ < target).toList

    primes.size
  }
}
