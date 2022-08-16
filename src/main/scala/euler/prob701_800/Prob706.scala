package euler.prob701_800

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob706 extends UtilResult {

  override def calc: Long = {

    var count = 0
    for (k <- 10 to 99) {

      val str = k.toString
      val n1 = str.take(1).toInt % 3
      val n2 = str.takeRight(1).toInt % 3
      val n3 = (n1 + n2) % 3

      if (n1 % 3 == 0 && n2 % 3 == 0 && n3 % 3 == 0) {
        println(k)
        count += 1
      } else if (n1 % 3 != 0 && n2 % 3 != 0 && n3 % 3 != 0) {
        println(k)
        count += 1
      }
    }

    println(count)

//    println(9 / 3)
//    println(99 / 3)
//    println(99 / 3 - 9 / 3)
//
//    println(99999 / 3)
//    println(999999 / 3)
//    println(999999 / 3 - 99999 / 3)

    0
  }
}
