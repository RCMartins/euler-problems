package euler.prob401_500.prob461

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob461 extends Util {

  def main(args: Array[String]): Unit = {

    def f(n: Int)(k: Int): Double = {
      math.pow(math.E, k.toDouble / n) - 1
    }

    //    println(math.Pi)
    //    println(f200(6) + f200(75) + f200(89) + f200(226))

    def N = 10000

    def f10 = f(N) _


    val v = for {
      k <- Stream.from(1)

    //      a <- Stream.from(1)
    //      b <- Stream.from(a)
    //      if a * a + b + c + d == N
    //      c <- Stream.from(b)
    //      d <- Stream.from(c)
    //      if a + b + c + d == N
    } yield f10(k)

    v.take(20000).toList.foreach(println)

  }

}
