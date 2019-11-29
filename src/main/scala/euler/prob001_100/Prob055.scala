package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob055 extends UtilResult {
  def calc: Long = {
    def testLychrel(n: BigInt, triesLeft: Int): Boolean = {
      if (triesLeft == 0)
        false
      else if (n.isPalindrome)
        true
      else
        testLychrel(n + n.reverseDigits, triesLeft - 1)
    }

    val v = for {
      n <- 1 to 10000
      if !testLychrel(BigInt(n) + BigInt(n).reverseDigits, 50)
    } yield n

    v.size
  }
}
