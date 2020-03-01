package euler.prob101_200.prob103

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob103 extends Util {

  def main(args: Array[String]): Unit = {
    val middle = 20
    val sol6 = List(11, 18, 19, 20, 22, 25)

    val near7 = middle :: sol6.map(_ + middle)

    result(near7.mkString)
  }

}
