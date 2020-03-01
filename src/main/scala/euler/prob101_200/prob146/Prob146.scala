package euler.prob101_200.prob146

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob146 extends Util {

  def main(args: Array[String]): Unit = {

   println( List(
      10,
      315410,
      927070,
      2525870,
      8146100,
      16755190,
      39313460
    ).sum) // there is more!

    val Max = 150e6.toInt
    val Max2 = 151e6.toInt

    val tests = List(1, 3, 7, 9, 13, 27).map(_.toLong)
    val negTests = ((1L to 27L).toSet -- tests.toSet).toList.sorted

    val primes =
      timeOnly {
        val primes = {
          SMALL_PRIMES.view.takeWhile(_ < Max2).map(_.toLong).toList
        }

        println("Total primes: " + primes.size)

        primes
      }

    def isPrimePattern(x2: Long): Boolean = {
      tests.forall(plus => testIsPrimeLongNoCache(x2 + plus, primes)) &&
        negTests.forall(plus => !testIsPrimeLongNoCache(x2 + plus, primes))
    }

    var n = 95e6.toInt

    // 0 TO 39 && 79 TO 95

    while (n < Max) {
      if (isPrimePattern(n.toLong * n)) {
        println(n)
      }
      if (n % 1000000 == 0)
        println(s"PROGRESS: $n")
      n += 1
    }
  }

}
