package euler.prob501_600.prob578

import euler.traits.Util

import scala.collection.mutable

/** Created by Ricardo on 01-Jan-17.
  */
object Prob578 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e13.toLong
    val MAX2 = MAX / 2
    val MAXSQ = math.floor(math.sqrt(MAX2)).toLong

    val primes = ALL_PRIMES.takeWhile(_ <= MAXSQ)
    println(primes.take(20).force)
    val revPrimes = primes.reverse

    case class NumberPP(val n: Long, val p1: Long, val p2: Long) {

//      def canEqual(other: Any): Boolean = other.isInstanceOf[NumberPP]
//
//      override def equals(other: Any): Boolean = other match {
//        case that: NumberPP =>
//          (that canEqual this) &&
//            n == that.n
//        case _ => false
//      }
//
//      override def hashCode(): Int = {
//        val state = Seq(n)
//        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
//      }
//
//      override def toString: String = n.toString
    }

    // @tailrec
    //    def loop(before: LazyList[Long], n: Long, primes: LazyList[Long]): LazyList[(Long, LazyList[Long])] = {
    def loop(before: LazyList[Long], primes: LazyList[Long]): LazyList[NumberPP] = {
      primes match {
        case prime #:: others =>
          exponents(prime).zipWithIndex
            .drop(2)
            .takeWhile(pair => pair._1 > 0 && pair._1 < MAX2)
            .flatMap {
              //            case (n, exp) => ALL_PRIMES.flatMap(primeSmaller => expoents(primeSmaller).slice(1, exp).map(smallerFact => (n * smallerFact, prime, primeSmaller)).filter(_._1 < MAX))
              case (n, exp) =>
                ALL_PRIMES
                  .takeWhile(primeSmaller => primeSmaller < prime && primeSmaller * n < MAX)
                  .flatMap(primeSmaller =>
                    exponents(primeSmaller)
                      .slice(1, exp)
                      .map(smallerFact => new NumberPP(n * smallerFact, prime, primeSmaller))
                      .filter(_.n < MAX)
                  )
            } #::: loop(before #::: LazyList(prime), others)
        case LazyList() => LazyList()
      }
    }

    val t = System.currentTimeMillis()

    val v = loop(LazyList(), revPrimes)

//    v.take(10).force
//    println(v.force)

    val directInvalid = v.size
    println(("directInvalid", directInvalid))

    println("directInvalid time: " + (System.currentTimeMillis() - t))

//    val indirectInvalid = {
//      def newStream(stream: LazyList[NumberPP]): LazyList[NumberPP] = {
//        stream.flatMap { numberPP =>
//            ALL_PRIMES.filter(prime => prime != numberPP.p1 && prime != numberPP.p2).takeWhile(_ * numberPP.n < MAX).map(primeExtra => new NumberPP(primeExtra * numberPP.n, numberPP.p1, numberPP.p2))
//        } match {
//          case LazyList() => LazyList()
//          case s => {
//            println("indirect loop time: " + (System.currentTimeMillis() - t), "size: " + s.size)
//            s #::: newStream(s)
//          }
//        }
//      }
//      newStream(v)
//    }.map(_.n).distinct

    val indirectInvalid = {
      val hash = mutable.HashSet[NumberPP]()
      hash ++= v
      def newStream(stream: LazyList[NumberPP]): Unit = {
        val lastSize = hash.size
        stream.foreach { num =>
          ALL_PRIMES
            .filter(prime => prime != num.p1 && prime != num.p2)
            .takeWhile(_ * num.n < MAX)
            .foreach(primeExtra => hash += new NumberPP(primeExtra * num.n, num.p1, num.p2))
        }
        if (lastSize != hash.size) {
          println(("indirect loop time: " + (System.currentTimeMillis() - t), "size: " + hash.size))
          val hashStream = LazyList.from(hash)
          newStream(hashStream)
        }
      }

      newStream(v)
      hash.map(_.n).toList.distinct
    }

//    println(indirectInvalid.take(10).force)

    // (1124,96275)
    // (1124,72472)

//    println(indirectInvalid)

    println((directInvalid, indirectInvalid.size))
    result(MAX - (directInvalid + indirectInvalid.size))
    result(MAX - indirectInvalid.size)

    println("total time: " + (System.currentTimeMillis() - t))
  }

}

//package euler.prob578
//
//import euler.Util
//
//import scala.annotation.tailrec
//
///**
//  * Created by Ricardo on 01-Jan-17.
//  */
//object Prob578 extends Util {
//
//  def main(args: Array[String]): Unit = {
//
//    val MAX = 1e2.toLong
//    //    val MAXSQ = math.floor(math.sqrt(MAX / 2)).toLong
//    //    println(MAX, MAXSQ) //3162253
//
//    val primesMAX = ALL_PRIMES.takeWhile(_ < MAX).map(_.toInt)
//    //    val primesSQ = primesMAX.takeWhile(_ < MAXSQ)
//    //    val revPrimes = primes.reverse
//
//    val size = primesMAX.size
//    //    println(primes)
//    println(size)
//
//    //    println(MAX)
//    //    println(primes.last * primes.last * 2)
//
//    //    val last = primes.last
//    //    val nextP = ALL_PRIMES.dropWhile(_ <= last).head
//    //    println(nextP * nextP * 2)
//
//    /*def loop(n: Long, index: Int, maxExp: Int): Int = {
//      if (maxExp == 0 || index == size) {
//        1
//      } else {
//        val base = primes(index)
//        (0 to maxExp).map(exp => {
//          val npow = n * math.pow(base, exp).toLong
//          if (npow < MAX)
//            loop(npow, index + 1, exp)
//          else
//            0
//        }).sum
//      }
//    }*/
//
//    //    @tailrec
//    //    def loopList(n: Long, primes: LazyList[Int], maxExp: Int): LazyList[Long] = {
//    //      primes match {
//    //        case LazyList() => LazyList(n)
//    //        case prime #:: others =>
//    //          (0 to maxExp).toStream.flatMap(exp => {
//    //            val npow = n * math.pow(prime, exp).toLong
//    //            if (npow < MAX) {
//    //              if (exp == 0)
//    //                loopList(npow, others, maxExp)
//    //              else
//    //                loopList(npow, others, exp)
//    //            } else
//    //              Nil
//    //          })
//    //      }
//    //    }
//
//    @tailrec
//    def loopList(n: Long, primes: LazyList[Int], exp: Int, maxExp: Int): LazyList[Long] = {
//      primes match {
//        case LazyList() => LazyList(n)
//        case prime #:: others =>
//          if (exp > maxExp) {
//            LazyList()
//          } else {
//            val npow = n * math.pow(prime, exp).toLong
//            if (npow < MAX) {
//              if (exp == 0)
//                loopList(npow, primes, exp + 1, maxExp)
//              else
//                loopList(npow, primes, exp + 1, exp)
//            } else
//              LazyList()
//          }
//        //          (0 to maxExp).toStream.flatMap(exp => {
//        //
//        //          })
//      }
//    }
//
//    //    def loop(n: Long, primes: LazyList[Int], maxExp: Int): Int = {
//    //      if (maxExp == 0 || primes.isEmpty) {
//    //        1
//    //      } else {
//    //        primes match {
//    //          case prime #:: others =>
//    //            (0 to maxExp).map(exp => {
//    //              val npow = n * math.pow(prime, exp).toLong
//    //              if (npow < MAX) {
//    //                if (exp == 0)
//    //                  loop(npow, others, maxExp)
//    //                else
//    //                  loop(npow, others, exp)
//    //              } else
//    //                0
//    //            }).sum
//    //        }
//    //      }
//    //    }
//
//    val t = System.currentTimeMillis()
//
//    //    for {
//    //      prime <- revPrimes
//    //    }
//
//
//    def loop2(primes: LazyList[Int]): LazyList[(Int, LazyList[Int])] = primes match {
//      case prime #:: tail => (prime, tail) #:: loop2(tail)
//      case LazyList() => LazyList()
//    }
//
//    val v = for {
//      (prime, tail) <- loop2(primesMAX)
//      (head, index) <- expoents(prime).takeWhile(_ < MAX).zipWithIndex.tail
//    } yield loopList(head, tail, 0, index).force
//    //    val v = loop(1, 0, 1)
//
//    println("time:", System.currentTimeMillis() - t)
//
//
//    //    println(v.mkString("\n"))
//    //    println(v.flatten.sorted.force)
//    //    println((1 until 100).toSet -- v.flatten.sorted.force.toSet)
//    println(v.flatten.size + 2)
//
//    //    for {
//    //      n <- 1 to 100
//    //    } println(n + " = " + factors(n).map { case (base, exp) => if (exp == 1) base else base + "^" + exp }.mkString(" * "))
//
//    //    val prin = for {
//    //      n <- 1 to 100
//    //    } yield factors(n).map { case (base, exp) => if (exp == 1) base else base + "^" + exp }.mkString(" * ")
//    //
//    //    println(prin.sorted.mkString("\n"))
//  }
//
//
//}
