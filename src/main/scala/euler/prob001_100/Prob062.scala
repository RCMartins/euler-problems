package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob062 extends UtilResult {
  override def calc: Long = {
    val hash = collection.mutable.HashMap[String, (Int, Int)]()

    NATURALS.takeWhile(_ < 50000).map(n => (n, BigInt(n).pow(3))).foreach {
      case (n, cube) =>
        val ord = cube.toString.sorted
        if (!hash.contains(ord)) {
          hash.put(ord, (1, n))
        } else {
          val (amount, small) = hash(ord)
          hash.update(ord, (amount + 1, small))
        }
    }

    val v = hash.values.filter { case (n, _) => n == 5 }.map { case (_, minV) => minV }.min

    math.pow(v, 3).toLong
  }
}
