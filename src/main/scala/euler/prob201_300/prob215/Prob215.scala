package euler.prob201_300.prob215

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob215 extends Util {

  def main(args: Array[String]): Unit = {
    time {

      val Size1 = 2
      val Size2 = 3

      def loop(size: Int, max: Int): List[List[Int]] = {
        val s1 =
          if (size + Size1 <= max)
            loop(size + Size1, max).flatMap {
              list =>
                if (size + Size1 <= max)
                  List(size + Size1 :: list)
                else
                  Nil
            }
          else
            List(Nil)
        val s2 =
          if (size + Size2 <= max)
            loop(size + Size2, max).flatMap {
              list =>
                if (size + Size2 <= max)
                  List(size + Size2 :: list)
                else
                  Nil
            }
          else
            List(Nil)
        s1 ++ s2
      }

      val Width = 32
      val Height = 10

      val all = loop(0, Width).filter(list => list.last == Width).map(_.init.toSet).distinct.toVector
      println(all.size)

      val array = Array.ofDim[Long](all.size, Height )
      for {
        t <- all.indices
        h <- 1 until Height
      } array(t)(h) = -1

      for (t <- all.indices)
        array(t)(0) = 1

      def calc(t: Int, h: Int): Long = {
        if (array(t)(h) == -1) {
          array(t)(h) = {
            val listT = all(t)
            var sum = 0L
            for (a <- all.indices if !listT.exists(all(a)))
              sum += calc(a, h - 1)
            sum
          }
        }
        array(t)(h)
      }

      all.indices.map(index => calc(index, Height - 1)).sum
    }
  }

}
