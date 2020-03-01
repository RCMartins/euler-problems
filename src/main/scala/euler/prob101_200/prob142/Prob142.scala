package euler.prob101_200.prob142

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob142 extends Util {

  def main(args: Array[String]): Unit = {

    //val queue = mutable.PriorityQueue[(Int, Int, Int)]()


    val ALL_SQUARES = NATURALS.map(n => n * n)

    val isSquare = ALL_SQUARES.takeWhile(n => n.toLong * n.toLong < Integer.MAX_VALUE).toSet
    val yz = for {
      square1 <- ALL_SQUARES
      z <- 1 to square1 / 2
      y = square1 - z
      // checked: y + z
      if y > z && isSquare(y - z)
    } yield (y, z)

    //    val yz = for {
    //      square1 <- ALL_SQUARES
    //      square2 <- ALL_SQUARES.takeWhile(_ < square1)
    //      (y, z) = {
    //        if (square1 % 2 == 0)
    //      }
    //      z <- 1 to square1 / 2
    //      y = square1 - dist1
    //      // checked: y + z
    //      if y > z && isSquare(y - z)
    //    } yield (y, z)

    //println(yz.take(100).force)

    val res = yz.takeWhile(pair => pair._1 + pair._2 < 1000000000)
    println("part 1 done")

    val omg = (res ++ res.map(_.swap)).groupBy(_._1).filter(_._2.size > 1).map { case (k, v) => (k, v.map(_._2)) }
    println("part 2 done")

    //println(omg)

    //x + y, x − y, x + z, x − z, y + z, y − z

    for {
      (a, yxList) <- omg
      List(b, c) <- yxList.combinations(2).map(_.toList)
      if isSquare(b + c) && (b > c && isSquare(b - c) || (b < c) && isSquare(c - b))
    //List(z, y, x) = List(a, b, c).sorted
    //if isSquare(x + y) && isSquare(x - y) && isSquare(x + z) && isSquare(x - z) && isSquare(y + z) && isSquare(y - z)
    } result(((a, b, c), a + b + c))

  }

}
