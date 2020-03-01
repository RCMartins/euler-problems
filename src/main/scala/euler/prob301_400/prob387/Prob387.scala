package euler.prob301_400.prob387

import euler.traits.Util

import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob387 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e14.toLong

    val q = mutable.Queue[Long]()
    var allHarshad: List[Long] = (1L to 9L).toList

    q ++= allHarshad

    while (q.nonEmpty) {
      val number = q.dequeue()

      ('0' to '9').foreach(digit => {
        val newNumber = (number.toString + digit).toLong
        if (newNumber < MAX) {
          val sum = digits(newNumber).sum
          if (newNumber % sum == 0) {
            //            if (testIsPrime(newNumber / sum)) {
            allHarshad = newNumber :: allHarshad
            q += newNumber
            //            }
          }
        }
      })
    }

//    println("right harshad", allHarshad.sorted)
//
//    println("strong right harshad", allHarshad.filter { number => {
//      val sum = digits(number).sum
//      testIsPrime(number / sum)
//    }
//    }.sorted)

    result(allHarshad.filter { number => {
      val sum = digits(number).sum
      testIsPrime(number / sum)
    }
    }.flatMap { number =>
      ('0' to '9').flatMap(digit => {
        val newNumber = (number.toString + digit).toLong
        if (newNumber < MAX && testIsPrime(newNumber))
          List(newNumber)
        else
          Nil
      })
    }.sum)


//    println(allHarshad.filter(testIsPrime(_)).sum)
//
//    println(allHarshad.size)
//    result(allHarshad.sum)
//
//    result(List(181, 211, 271, 277, 421, 457, 631, 2011, 2017, 2099, 2473, 2477, 4021, 4027, 4073, 4079, 4231, 4813, 4817, 6037, 8011, 8017, 8039, 8461, 8467).sum)

  }

}
