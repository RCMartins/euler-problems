package euler.prob201_300.prob268

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob268 extends Util {

  def main(args: Array[String]): Unit = {

    val Max = 1e16.toLong
    println(Max)
    val primes = ALL_PRIMES.takeWhile(_ < 100)

    //    println(primes.size)
    //    println(primes.mkString("\n"))

    //    println(primes.toSet.subsets(4).size)
    //    println(primes.toSet.subsets(4).map(_.toList.sorted).toList.sortBy(_.head).mkString("\n"))

    def count(list: List[Long], initial: Long): Long = list match {
      case Nil => 1
      case x :: xs =>
        //        println(initial)
        val v = exponents(x).takeWhile(_ * initial < Max)
        //        println(v.force)

        v.foldLeft(0L)((amount, mult) => amount + count(xs, initial * mult))
    }

//    println((4 to primes.size).map(a => (a, primes.take(a).product)).takeWhile(_._2 < Max).mkString("\n"))
//    println((4 to 13).map(a => (a, primes.takeRight(a).product)).mkString("\n"))

    time {
      var total, total2 = 0L
      for {
        size <- 4 to primes.size
        list <- primes.toSet.subsets(size)
        pro = list.product
        if pro < Max
      } {
        val v = count(list.toList, pro)
        total += v
        total2 += v
        if (total2 > 10000000) {
          total2 -= 10000000
          println((total, size))
        }
      }

      total
    }
  }

}
