package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob065 extends UtilResult {
  def calc: Long = {
    def streamE(n: Int): LazyList[Int] = 1 #:: n #:: 1 #:: streamE(n + 2)
    val eDiv = streamE(2)

    def streamSeqE(n: Int): LazyList[(BigInt, BigInt)] = {
      val result =
        eDiv.take(n).foldRight((BigInt(0), BigInt(1))) { case (elem, (a, b)) => (b, a + b * elem) }

      (result._1 + result._2 * 2, result._2) #:: streamSeqE(n + 1)
    }

    val trueEStream = (BigInt(2), BigInt(1)) #:: streamSeqE(1)

//    2    = 2.0
//    3    = 2.0+(1/1)
//    8/3  = 2.0+(1/(1+(1/2.0)))
//    11/4 = 2.0+(1/(1+(1/(2.0 + 1))))

    println(eDiv.take(10).toList)

    trueEStream.zip(1 to 200).drop(99).head._1._1.toString.map(_ - '0').sum
  }
}
