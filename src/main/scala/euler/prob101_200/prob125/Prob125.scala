package euler.prob101_200.prob125

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob125 extends Util {

  def main(args: Array[String]): Unit = {

    val MaxValue = 1e8.toInt
    val MAX = 8

    time {
      val squares = LazyList.from(1).map(n => n * n).takeWhile(_ < MaxValue).toVector

      def createPalindrome(size: Int): Seq[String] = {
        if (size == 0)
          List("")
        else
          ('0' to '9').flatMap(c => createPalindrome(size - 1).map(_ + c)).filterNot(v => v.startsWith("0"))
      }

      val v =
        for {
          size <- 1 to MAX
          p <- {
            val size2 = if (size % 2 == 0) size / 2 else size / 2 + 1
            for {
              palindrome <- createPalindrome(size2)
              p = if (size % 2 == 0) palindrome + palindrome.reverse else palindrome + palindrome.init.reverse
            } yield p
          }
        } yield p.toInt

      def testIsPalindromic(n: Int): Boolean = {
        def aux(acc: Int, min: Int, max: Int): Boolean = {
          if (acc > n) {
            if (max - min > 2) {
              aux(acc - squares(min), min + 1, max)
            } else {
              false
            }
          } else if (acc < n) {
            if (max < squares.size) {
              aux(acc + squares(max), min, max + 1)
            } else {
              false
            }
          } else {
            true
          }
        }

        aux(5, 0, 2)
      }

      v.filter(testIsPalindromic).map(_.toLong).sum
    }
  }

}
