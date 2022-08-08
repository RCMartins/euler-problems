package euler.prob201_300.prob293

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob293 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e9.toInt
    println(MAX)

    val all = SMALL_PRIMES.take(9).toList
    println(all)
    println(all.product)

    def exp(base: Int) = exponents(base)

    def admissible(number: Long, primes: List[Int]): LazyList[Long] = {
      primes match {
        case Nil => LazyList()
        case prime :: rest => exp(prime).drop(1).takeWhile(_ * number < MAX).flatMap{
          factor =>
            val n = number * factor
            n #:: admissible(n, rest)
        }
      }
    }

    val allAdmissible = admissible(1, all).toList.sorted

    val allPseodoFortunate = allAdmissible.map( adm => {
      LazyList.from(2).dropWhile(m => !testIsPrime(adm + m)).head
    })

//    result(allAdmissible.take(20))
//    result(allPseodoFortunate.take(20))
//    result(allPseodoFortunate.distinct.sorted)
    result(allPseodoFortunate.distinct.sum)

  }

}
