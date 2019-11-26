package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob030 extends UtilResult {
  def calc: Long = {
    (1 to math.pow(9, 5).toInt * 6)
      .filter(num => num == num.toString.map(d => math.pow(d.asDigit, 5)).sum)
      .sum - 1
  }
}
