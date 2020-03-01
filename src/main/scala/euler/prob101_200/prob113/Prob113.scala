package euler.prob101_200.prob113

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob113 extends UtilResult {

  def calc: Long = {
    val maxDigits = 100

    def equal: Int = 9 * maxDigits

    val incArr: Array[Array[Long]] = Array.fill(10, maxDigits)(0)
    val decArr: Array[Array[Long]] = Array.fill(10, maxDigits)(0)

    def inc(lastDigit: Int, pos: Int): Long = {
      if (pos == maxDigits)
        10 - lastDigit
      else {
        if (incArr(lastDigit)(maxDigits - pos) == 0) {
          incArr(lastDigit)(maxDigits - pos) =
            (lastDigit to 9).map(digit => inc(digit, pos + 1)).sum
        }
        incArr(lastDigit)(maxDigits - pos)
      }
    }

    def dec(lastDigit: Int, pos: Int): Long = {
      if (pos == maxDigits)
        lastDigit + 1
      else {
        if (decArr(lastDigit)(maxDigits - pos) == 0) {
          decArr(lastDigit)(maxDigits - pos) =
            (0 to lastDigit).map(digit => dec(digit, pos + 1)).sum
        }
        decArr(lastDigit)(maxDigits - pos)
      }
    }

    inc(0, 1) + (1 to maxDigits).map(n => dec(9, n)).sum - equal - maxDigits - 1
  }

}
