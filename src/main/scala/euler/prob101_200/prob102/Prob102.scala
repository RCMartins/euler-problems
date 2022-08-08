package euler.prob101_200.prob102

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob102 extends Util {

  type Pair = (Double, Double)

  def sign(p1: Pair, p2: Pair, p3: Pair): Double = (p1._1 - p3._1) * (p2._2 - p3._2) - (p2._1 - p3._1) * (p1._2 - p3._2)

  def PointInTriangle(pt: Pair, v1: Pair, v2: Pair, v3: Pair): Boolean = {
    val b1 = sign(pt, v1, v2) < 0.0f
    val b2 = sign(pt, v2, v3) < 0.0f
    val b3 = sign(pt, v3, v1) < 0.0f
    (b1 == b2) && (b2 == b3)
  }

  def main(args: Array[String]): Unit = {

    val inputStream = new FileInputStream("data\\p102_triangles.txt")
    val br = new BufferedReader(new InputStreamReader(inputStream))

    def readAll(): LazyList[List[Double]] = {
      readLine(br) match {
        case Some(text) =>
          if (text != null) {
            val list = text.split(',').toList.map(_.toDouble)
            list #:: readAll()
          } else
            LazyList()
        case None =>
          LazyList()
      }
    }

    time {
      val all = readAll().map {
        case List(a, b, c, d, e, f) =>
          PointInTriangle((0.0, 0.0), (a, b), (c, d), (e, f))
      }

      all.count(identity)
    }
  }

}
