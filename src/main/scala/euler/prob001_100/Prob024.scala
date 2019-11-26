package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob024 extends UtilResult {
  def calc: Long = {
    val list = (0 to 9).permutations.toStream.map(_.mkString)
    list(999999).toLong
  }
}
