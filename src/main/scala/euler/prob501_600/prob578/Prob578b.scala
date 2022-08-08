package euler.prob501_600.prob578

import euler.traits.Util

import scala.collection.mutable

/** Created by Ricardo on 01-Jan-17.
  */
object Prob578b extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e13.toLong
    val MAX2 = MAX / 2
    val MAXSQ = math.floor(math.sqrt(MAX2)).toLong

    val primes = ALL_PRIMES.takeWhile(_ <= MAXSQ)
    println(primes.take(20).force)
    val revPrimes = primes.reverse

    println(revPrimes.head * revPrimes.head)
    println(revPrimes.head * revPrimes.head * revPrimes.head)

    case class NumberPP(n: Long, p1: Long, p2: Long) {

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

    // confirmed correct, probably a little inefficient
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

    result(MAX - (directInvalid + indirectInvalid.size))
    result(MAX - indirectInvalid.size)

    println("total time: " + (System.currentTimeMillis() - t))
  }

}
