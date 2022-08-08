package euler.prob101_200.prob200

import euler.traits.Util

import scala.collection.mutable

/** Created by Ricardo
  */
object Prob200 extends Util {

  @inline private def mult(a: Long, b: Long): Long = a * a * b * b * b

  def main(args: Array[String]): Unit = {

    time {
      trait PrimesCons

      case class MainPrimes(all: LazyList[LazyList[Long]]) extends PrimesCons
      case class LinePrimes(head: Long, others: LazyList[Long]) extends PrimesCons
      case object NoPrimes extends PrimesCons

      val queue = mutable.PriorityQueue[(Long, PrimesCons)]()(
        (x: (Long, PrimesCons), y: (Long, PrimesCons)) => y._1.compareTo(x._1)
      )
      queue += ((mult(2, 3), NoPrimes))
      queue += ((mult(3, 2), LinePrimes(ALL_PRIMES.head, ALL_PRIMES.drop(2))))
      queue += ((mult(3, 5), NoPrimes))
      queue += ((mult(5, 3), MainPrimes(LazyList.from(ALL_PRIMES.tails).tail)))

      def getAllSqubes: LazyList[Long] = {
        val (smaller, nextContinuation) = queue.dequeue()
        nextContinuation match {
          case NoPrimes =>
          case LinePrimes(head, next #:: tail) =>
            queue += ((mult(head, next), NoPrimes))
            queue += ((mult(next, head), LinePrimes(head, tail)))
          case MainPrimes(
                (head #:: _ #:: next #:: tail) #:: (head2 #:: next2 #:: tail2) #:: rest
              ) =>
            queue += ((mult(head, next), NoPrimes))
            queue += ((mult(next, head), LinePrimes(head, tail)))
            queue += ((mult(head2, next2), NoPrimes))
            queue += ((mult(next2, head2), MainPrimes((head2 #:: next2 #:: tail2) #:: rest)))
        }
        smaller #:: getAllSqubes
      }

      def isPrimeProof(number: Long): Boolean = {
        val str = number.toString
        !str.zipWithIndex.exists { case (dChar, index) =>
          ('0' to '9')
            .filterNot(_ == dChar)
            .exists(d => {
              val n = str.updated(index, d).toLong
              n > 0 && testIsPrime(n)
            })
        }
      }

      def has200SubString(number: Long): Boolean = {
        number.toString.contains("200")
      }

      getAllSqubes.filter(n => isPrimeProof(n) && has200SubString(n)).drop(199).head
    }
  }

}
