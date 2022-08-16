package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob024 extends UtilResult {

  override def calc: Long = {
    val list = LazyList.from((0 to 9).permutations).map(_.mkString)
    list(999999).toLong
  }

}
