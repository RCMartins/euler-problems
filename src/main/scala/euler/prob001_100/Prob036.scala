package euler.prob001_100

import euler.traits.UtilResult

import scala.runtime.RichInt

/**
  * Created by Ricardo
  */
object Prob036 extends UtilResult {
  def calc: Long = {
    val vals = for {
      num10 <- 1 until 1000000 by 2
      if num10.toString == num10.toString.reverse
      num2 = new RichInt(num10).toBinaryString
      if num2 == num2.reverse
    } yield num10

    vals.sum
  }
}
