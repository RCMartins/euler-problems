package euler.prob401_500.prob425

import euler.traits.Util

import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob425 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e7.toInt
    val primes = ALL_PRIMES.takeWhile(_ < MAX).map(_.toInt)

    case class Relative(prime: Int, max: Int)

    val seen = mutable.HashMap[Int, Relative]()
    seen += 2 -> Relative(2, 2)

    val queue = mutable.Queue[Relative]()
    queue += Relative(2, 2)

    while (queue.nonEmpty) {
      val Relative(prime, max) = queue.dequeue()
      val nStr = prime.toString
      val l1 = if (nStr.length < 7) ('1' to '9').map(digit => digit + nStr).toList else Nil
      val l2 = if (nStr.length > 1) List(nStr.tail).filter((numberStr: String) => !numberStr.startsWith("0")) else Nil
      val l3 = (0 until nStr.length)
        .flatMap(index => (0 to 9).map(n => nStr.updated(index, n.toString.charAt(0)).mkString))
        .filter((numberStr: String) => !numberStr.startsWith("0"))

      val all = (l1 ++ l2 ++ l3).map(_.toInt).filter(number => number != prime && number < MAX && testIsPrime(number))

      for (nextPrime <- all) {
        val newMax = math.max(max, nextPrime)
        seen.get(nextPrime) match {
          case Some(Relative(_, seenMax)) =>
            if (seenMax > newMax) {
              val elem = Relative(nextPrime, newMax)
              queue += elem
              seen += nextPrime -> elem
            }
          case None =>
            val elem = Relative(nextPrime, newMax)
            queue += elem
            seen += nextPrime -> elem
        }
      }
    }

    val hashPrimes = mutable.HashSet[Int]()
    hashPrimes ++= primes
    hashPrimes --= seen.filter(pair => pair._1 == pair._2.max).keySet

    //    seen.keySet.foreach(hashPrimes -= _)
    //    println(hashPrimes.toList.sorted)
    //    println(seen.toList.unzip._1.sorted)

//    for (pair <- seen.toList.sortBy(_._1)) {
//      if (pair._1 != pair._2.max)
//        println(pair)
//    }

    result(hashPrimes.foldLeft(0L)(_ + _))

  }

}
