package euler.prob501_600

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob516 extends UtilResult {

  /*
  old code:
  val v = for {
    n <- 1 to 10
  } yield (1 to n).filter(GCD(_, n) == 1)

  println(v)

  val v2 = for {
    n <- 1 to 100
    if factors((1 to n).count(GCD(_, n) == 1)).forall { case (prime, _) => prime <= 5 }
  } yield n

  println(v2.sum)
   */

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
