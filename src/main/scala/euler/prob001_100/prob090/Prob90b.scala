package euler.prob001_100.prob090

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob90b extends UtilResult {
  def contains(pair: Set[Int], elem: Int): Boolean = {
    if (elem == 6 || elem == 9)
      pair(6) || pair(9)
    else
      pair(elem)
  }

  def calc: Long = {
    time {
      val input = List((0, 1), (0, 4), (0, 9), (1, 6), (2, 5), (3, 6), (4, 9), (6, 4), (8, 1))

      //    println((0 to 9 ).toSet.subsets(6).size)

      val cubes = (0 to 9).toSet.subsets(6).toList

      val v =
        for {
          cubeA <- cubes
          cubeB <- cubes
          if {
            input.forall {
              case (a, b) =>
                if (cubeA(a))
                  contains(cubeB, b)
                else if (cubeB(a))
                  contains(cubeA, b)
                else
                  false
            }
          }
        } yield {
          val sa = cubeA.toList.sorted.mkString
          val sb = cubeB.toList.sorted.mkString
          if (sa < sb)
            (sa, sb)
          else
            (sb, sa)
        }

      println(v.mkString("\n"))
      v.distinct.size
    }
  }
}
