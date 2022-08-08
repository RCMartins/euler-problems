package euler.prob201_300.prob229

import euler.traits.Util

import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob229 extends Util {

  def main(args: Array[String]): Unit = {

    time {
      val Max = 2e9.toInt
      val squaresList = LazyList.from(1).map(n => n * n).takeWhile(_ < Max).toList
      val mapping = mutable.LongMap[Int]()
      val multList = List((1, 0x01.toShort), (2, 0x02.toShort), (3, 0x04.toShort))

      val (mult, mask) = (7, 0x08.toShort)
      for {
        sq <- squaresList
        if (sq.toLong * mult) < Max
      } {
        val n = sq * mult
        for (elem <- squaresList.map(_ + n).takeWhile(_ < Max)) {
          mapping.get(elem) match {
            case None => mapping.update(elem, mask)
            case _ =>
          }
        }
      }

      println("Done X7")
      mapping.repack()

      for {
        sq <- squaresList
        (mult, mask) <- multList
        if (sq.toLong * mult) < Max
      } {
        val n = sq * mult
        for (elem <- squaresList.map(_ + n).takeWhile(_ < Max)) {
          mapping.get(elem) match {
            case None =>
            case Some(result) =>
              if ((result & mask) == 0)
                mapping.update(elem, (result | mask).toShort)
          }
        }
      }

      val full = 0xF
      mapping.count(_._2 == full)
    }

    //11325263, 688422ms, 12 GB RAM
  }

}
