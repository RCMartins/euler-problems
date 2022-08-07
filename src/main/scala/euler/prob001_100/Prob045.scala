package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob045 extends UtilResult {
  def calc: Long = {
    def intersectStream(a: LazyList[Long], b: LazyList[Long]): LazyList[Long] = {
      (a, b) match {
        case (x #:: xs, y #:: ys) =>
          if (x > y) intersectStream(a, ys)
          else if (y > x) intersectStream(xs, b)
          else x #:: intersectStream(xs, ys)
      }
    }

    def from = LazyList.from(1).map(_.toLong)

    val tri = from.map(n => n * (n + 1) / 2)
    val pen = from.map(n => n * (3 * n - 1) / 2)
    val hex = from.map(n => n * (2 * n - 1))

    intersectStream(intersectStream(tri, pen), hex).drop(2).head
  }
}
