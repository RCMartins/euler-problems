package euler.prob401_500

import euler.traits.UtilResult

import scala.collection.mutable

/** Created by Ricardo
  */
object Prob500 extends UtilResult {

  override def calc: Long = {
    val target = 500500
    val module = 500500507L

    val ordering: Ordering[(Long, Boolean)] = (x: (Long, Boolean), y: (Long, Boolean)) =>
      if (x._1 > y._1) -1 else if (x._1 < y._1) 1 else 0

    val priorityQueue: mutable.PriorityQueue[(Long, Boolean)] =
      mutable.PriorityQueue.empty(ordering)
    priorityQueue += ((SMALL_PRIMES.head.toLong, true))

    val allFactorsSorted: LazyList[Long] = {
      def loop(primes: LazyList[Long]): LazyList[Long] = {
        val (current, lastPrime) = priorityQueue.dequeue()
        priorityQueue += ((current * current, false))
        current #:: {
          if (lastPrime)
            primes match {
              case newPrime #:: nextPrimes =>
                priorityQueue += ((newPrime, true))
                loop(nextPrimes)
            }
          else
            loop(primes)
        }
      }
      loop(SMALL_PRIMES.tail.map(_.toLong))
    }

    allFactorsSorted.take(target).foldLeft(1L)((n, factor) => (n * factor) % module)
  }
}
