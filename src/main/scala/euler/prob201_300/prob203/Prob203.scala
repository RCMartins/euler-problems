package euler.prob201_300.prob203

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob203 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX_ROW = 51

    val row1 = Vector(0L, 1L, 0L)

    def calcRow(oldRow: Vector[Long]): Vector[Long] = {
      0L +: oldRow.zip(oldRow.tail).map { case (a, b) => a + b } :+ 0L
    }

    val pascalRows = (1 until MAX_ROW).scanLeft(row1)((row, _) => calcRow(row)).map(_.tail.init)
    val pascalNumbers = pascalRows.flatten.distinct.sorted

//    println(pascalRows.mkString("\n"))
//    println(pascalNumbers.size)

    val MAX_PRIME = math.sqrt(pascalNumbers.max).toInt

    val primes = SMALL_PRIMES.takeWhile(_ < MAX_PRIME).map(_.toLong)

    //    println(primes.size)

    time {
      val v = for {
        n <- pascalNumbers
        if primes.map(p => p * p).takeWhile(_ <= n).exists(n % _ == 0)
      } yield n

      (pascalNumbers.toSet -- v.toSet).sum
    }
  }

}
