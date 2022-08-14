package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob057 extends UtilResult {
  override def calc: Long = {
    val eDiv = LazyList.continually(2)

    def streamSeqE(n: Int): LazyList[(BigInt, BigInt)] = {
      val result =
        eDiv.take(n).foldRight((BigInt(0), BigInt(1))) { case (elem, (a, b)) => (b, a + b * elem) }

      (result._1 + result._2 * 1, result._2) #:: streamSeqE(n + 1)
    }

    val trueEStream = streamSeqE(1)

    trueEStream.take(1000).count { case (a, b) => a.toString.length > b.toString.length }
  }
}
