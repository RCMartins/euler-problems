package euler.prob701_800

import euler.traits.UtilResult

import scala.util.chaining.scalaUtilChainingOps

/** Created by Ricardo
  */
object Prob793 extends UtilResult {

  override def calc: Long = {
    // 1000003
    val target = 10000
    val s0 = 290797L
    val mod = 50515093L
//    val modMargin = BigInt((mod * 0.1).toLong).pow(2)
//    val minLimit = BigInt((mod * 0.5).toLong).pow(2) - modMargin
//    val maxLimit = BigInt((mod * 0.5).toLong).pow(2) + modMargin
    var minCount: Int = 0
    var maxCount: Int = 0

    // M(103)   = 492700616748525
    // M(5003)  = 465534708372414
    // M(10000) = 467242830304019

    def calcS(n: Long): LazyList[Long] = {
      val v: Long = (n * n) % mod
      v #:: calcS(v)
    }

    def sSeq: LazyList[Long] = s0 #:: calcS(s0)

    val sSeqIndexed = sSeq.take(target).toIndexedSeq

    def m: IndexedSeq[Long] =
      (0 until target - 1).flatMap(i =>
        (i + 1 until target).flatMap { j =>
          val value = sSeqIndexed(i) * sSeqIndexed(j)
//          if (value < minLimit) {
//            minCount += 1
//            None
//          } else if (value > maxLimit) {
//            maxCount += 1
//            None
//          } else
          Some(value)
        }
      )

    val ms: IndexedSeq[Long] =
      m.sorted

    val size: Int =
      ms.size + minCount + maxCount
    println(ms.size)
    println(minCount)
    println(maxCount)
    println(size)

    val pos = size / 2 - minCount - maxCount
    val result: Long =
      if (size % 2 == 0) {
        println(ms.slice(pos - 1, pos + 1).toList)
        ms.slice(pos - 1, pos + 1).toList.pipe { case List(a, b) => (a + b) / 2 }
      } else
        ms.slice(pos, pos + 1).head

    println()
//    println(minLimit)
    println(result)
//    println(maxLimit)
    println()

    result
  }

}
