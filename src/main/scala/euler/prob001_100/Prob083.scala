package euler.prob001_100

import euler.traits.UtilResult

import scala.collection.immutable.BitSet
import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob083 extends UtilResult {
  override def calc: Long = {
    val SIZE = 80
//    val (up, right, down, left) = (0, 1, 2, 3)
    val DX = Vector(0, 1, 0, -1)
    val DY = Vector(-1, 0, 1, 0)

//    val MaxValue = SIZE * SIZE * 100000

    val arr = Array.ofDim[Int](SIZE, SIZE)
    val data = readData("p083_matrix.txt").split("\n").map(_.split(",").map(_.toInt).toArray)
    for (x <- 0 until SIZE)
      for (y <- 0 until SIZE)
        arr(x)(y) = data(y)(x)

//    val sum = Array.fill[Int](SIZE, SIZE)(MaxValue)

    implicit val ord: Ordering[(Int, Int, Int, BitSet)] =
      (x: (Int, Int, Int, BitSet), y: (Int, Int, Int, BitSet)) => y._1 - x._1

    val queue = mutable.PriorityQueue[(Int, Int, Int, BitSet)]((arr(0)(0), 0, 0, BitSet(0)))

    def pos(x: Int, y: Int): Int = x + y * SIZE

    def best: Int = {
      while (queue.nonEmpty) {
        val (v, x, y, bit) = queue.dequeue()

        if (x == SIZE - 1 && y == SIZE - 1)
          return v

        for (dir <- 0 to 3) yield {
          val (nx, ny) = (x + DX(dir), y + DY(dir))
          if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && !bit(pos(nx, ny)))
            queue.enqueue((v + arr(nx)(ny), nx, ny, bit + pos(nx, ny)))
        }
      }
      ???
    }

//    def best(x: Int, y: Int): Int = {
//      println((x, y))
//      if (sum(x)(y) == MaxValue) {
//        val values =
//          for (dir <- List(1, 2, 0, 3)) yield {
//            val (nx, ny) = (x + DX(dir), y + DY(dir))
//            if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE)
//              best(nx, ny)
//            else
//              MaxValue
//          }
//        sum(x)(y) = values.min
//        println((x, y, values.min))
//      }
//      sum(x)(y)
//    }

    for (y <- 0 until SIZE) {
      for (x <- 0 until SIZE) {
        print(arr(x)(y) + " ")
      }
      println()
    }

    println()

//    for (y <- 0 until SIZE) {w
//      for (x <- 0 until SIZE) {
//        print(sum(x)(y) + " ")
//      }
//      println()
//    }

//    best(SIZE - 1, SIZE - 1)
    best
  }
}
