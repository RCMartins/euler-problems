package euler.prob401_500

import euler.traits.UtilResult

import scala.collection.mutable

/** Created by Ricardo
  */
object Prob516 extends UtilResult {

  def calc: Long = {
    val target = Math.pow(10, 12).toLong

    def powers(n: Int): IndexedSeq[Long] =
      LazyList.from(1).scanLeft(1L)((acc, _) => acc * n).takeWhile(_ <= target).toIndexedSeq.tail

    val all2Powers: IndexedSeq[Long] = powers(2)
    val all3Powers: IndexedSeq[Long] = powers(3)
    val all5Powers: IndexedSeq[Long] = powers(5)

    println(all2Powers)
    println(all3Powers)
    println(all5Powers)

    println(all2Powers.size + all3Powers.size + all5Powers.size)
    println(Math.pow(3, all2Powers.size + all3Powers.size + all5Powers.size))
    println(all3Powers.size + all5Powers.size)
    println(Math.pow(2, all3Powers.size + all5Powers.size).toLong)

    val allHamming: LazyList[Long] =
      ???

    ???
  }
}
