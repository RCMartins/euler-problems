package euler.prob501_600.prob577

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob577 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 12345

    def calcPascal(value: Long, index: Long): LazyList[Long] = {
      (value + index) #:: calcPascal(value + index, index + 1)
    }

    val pascal = calcPascal(0, 1).take(MAX+1).toVector
//    println(pascal.take(20))

    def calc(index: Int, multiplier: Int): Long = {
      if (index < 0)
        0L
      else
        pascal(index) * multiplier + calc(index - 3, multiplier + 1)
    }

    val v: Seq[Long] = for {
      n <- 3 to MAX
    } yield calc(n-3, 1)

//    println(v.take(20))
    result(v.sum)

  }

}
