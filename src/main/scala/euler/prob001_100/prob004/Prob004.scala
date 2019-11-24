package euler.prob001_100.prob004

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob004 extends UtilResult {

  def calc: Long = {

    val v = for {
      a <- 100 to 999
      b <- 100 to 999
      num = a * b
      if num.toString == num.toString.reverse
    } yield a * b

    v.max
  }

}
