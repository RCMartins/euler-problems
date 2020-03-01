package euler.prob101_200.prob173

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob173 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e6.toInt

    val bases = (8 to MAX by 8).scanLeft(0)(_ + _).takeWhile(_ <= MAX).tail
    println(bases)

    val v = for {
      (base, mult) <- bases.zip(4 to MAX by 4)
      value <- base to MAX by mult
      if value <= MAX
    } yield value

//    println(v)
    result(v.size)
    //1572729
  }

}
