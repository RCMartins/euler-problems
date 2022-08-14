package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob052 extends UtilResult {
  override def calc: Long = {
    val v =
      LazyList.from(1).filter(x => (2 to 6).forall(n => (n * x).toString.sorted == x.toString.sorted))
    v.head
  }
}
