package euler.prob301_400.prob348

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob348 extends Util {

  def main(args: Array[String]): Unit = {

    val squares = (1 to 1000000).map(n => n.toLong * n).toSet
    val cubes = (1 to 10000).map(n => n.toLong * n * n).toStream

    val v =
      for {
        nHalf <- LazyList.from(10).map(_.toString)
        n <- List((nHalf + nHalf.reverse).toLong, (nHalf + nHalf.init.reverse).toLong)
        if cubes.takeWhile(_ < n).count(cube => squares(n - cube)) == 4
      } yield n

    time(v.take(5).sum)

  }

}
