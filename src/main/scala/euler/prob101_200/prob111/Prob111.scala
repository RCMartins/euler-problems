package euler.prob101_200.prob111

import euler.traits.Util

/** Created by Ricardo
  */
object Prob111 extends Util {

  def main(args: Array[String]): Unit = {

    def findNext(repeatedDigit: Char, amountRepeated: Int, digitsLeft: Int): LazyList[String] = {
      if (digitsLeft == 0)
        LazyList("")
      else if (digitsLeft == amountRepeated) {
        LazyList(repeatedDigit.toString * digitsLeft)
      } else {
        LazyList.from('0' to '9').flatMap { d =>
          if (d == repeatedDigit) {
            if (amountRepeated > 0)
              findNext(repeatedDigit, amountRepeated - 1, digitsLeft - 1).map(d + _)
            else
              LazyList.empty
          } else
            findNext(repeatedDigit, amountRepeated, digitsLeft - 1).map(d + _)
        }
      }
    }

    def findNextPrime(repeatedDigit: Char, amountRepeated: Int, digitsLeft: Int): Long = {
      val s = findNext(repeatedDigit, amountRepeated, digitsLeft)
        .filter(number => !number.startsWith("0") && testIsPrime(number.toLong))
        .map(_.toLong)
        .sum
      if (s == 0)
        findNextPrime(repeatedDigit, amountRepeated - 1, digitsLeft)
      else
        s
    }

    val MAX = 10
    val v = ('0' to '9').map(d => findNextPrime(d, MAX - 1, MAX))

    println(v)
    result(v.sum)

  }

}