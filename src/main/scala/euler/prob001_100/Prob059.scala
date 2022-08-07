package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob059 extends UtilResult {
  def calc: Long = {
    val codes = readData("p059_cipher.txt").split(",").map(_.toInt).toVector

    println(codes.groupBy(x => x).toList.sortBy(-_._2.length))

    val v = for {
      c1 <- 'a' to 'z'
      c2 <- 'a' to 'z'
      c3 <- 'a' to 'z'
      cipherStr = List(c1, c2, c3).mkString
      cipher = Vector(c1, c2, c3).map(_.toInt)
    } yield (cipherStr, {
      for {
        (code, index) <- codes.zip(LazyList.from(0))
      } yield code ^ cipher(index % 3)
    }.map(_.toChar).mkString)

    val res = v.filter(pair => pair._2.contains(" and "))

    res.head._2.map(_.toInt).sum
  }
}
