package euler.prob001_100.prob090

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob090 extends UtilResult {
  def calc: Long = {
    val input = List((0, 1), (0, 4), (0, 9), (1, 6), (2, 5), (3, 6), (4, 9), (6, 4), (8, 1))

    def allCombs(in: List[(Int, Int)]): List[(Set[Int], Set[Int])] = in match {
      case Nil => List((Set(), Set()))
      case (a0, b0) :: others =>
        val list = allCombs(others)
        list.flatMap {
          case (s1, s2) =>
            def makeList(n: Int) = if (n == 6 || n == 9) List(6, 9) else List(n)

            for {
              a <- makeList(a0)
              b <- makeList(b0)
              elem <- List((s1 + a, s2 + b), (s1 + b, s2 + a))
            } yield elem
        }
    }

    def createAll(list: List[Int]): List[List[Int]] = {
      if (list.size == 6)
        List(list)
      else {
        val e = list.toSet
        (
          for {
            d <- 0 to 9 if !e(d)
            e <- createAll(d :: list)
          } yield e
        ).toList
      }
    }

    println(
      allCombs(input)
        .exists { case (s1, s2) => s1.size < 5 || s2.size < 5 }
    )

    val comb =
      allCombs(input)
        .filter { case (s1, s2) => s1.size <= 6 && s2.size <= 6 }
        .flatMap {
          case (s1, s2) =>
            createAll(s1.toList).flatMap(l1 => createAll(s2.toList).map(l1.sorted ++ _.sorted))
        }
        .distinct

    println(comb.mkString("\n"))
    comb.size
  }
}
