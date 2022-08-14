package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob012 extends UtilResult {
  def triNumber(initial: Long, n: Long): LazyList[Long] = {
    val v = initial + n
    LazyList.cons(v, triNumber(v, n + 1))
  }

  override def calc: Long = {
    val allTriang = triNumber(0, 1)

    var maxDivs = 0
    allTriang.find { trigN =>
      val primes = factors(trigN)
      if (primes.map(_._2).sum > 0) {
        //println(trigN + ": " + primes)

        val allFactors = primes.flatMap { case (prime, times) => List.fill(times)(prime) }
        val allUniqueDivisors = allFactors
          .foldLeft(List(1L))((list, prime) => list.flatMap(elem => List(elem, elem * prime)))
          .distinct
          .sorted
        //println(allUniqueDivisors)

        if (allUniqueDivisors.length > maxDivs) {
          maxDivs = allUniqueDivisors.length
          //result(maxDivs + " => " + trigN + " => " + allUniqueDivisors)
        }
      }
      if (maxDivs >= 500) {
        true
      } else
        false
    }.head
  }
}
