package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob040 extends UtilResult {

  override def calc: Long = {
    def champernowne(n: Int): LazyList[Int] =
      LazyList.from(n.toString.map(_ - '0')) #::: champernowne(n + 1)

    val allChamp = champernowne(1)
    allChamp(0) *
      allChamp(9) *
      allChamp(99) *
      allChamp(999) *
      allChamp(9999) *
      allChamp(99999) *
      allChamp(999999)
  }

}
