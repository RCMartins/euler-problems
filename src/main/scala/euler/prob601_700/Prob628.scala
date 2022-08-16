package euler.prob601_700

import euler.traits.UtilResult

import scala.collection.mutable

/** Created by Ricardo
  */
object Prob628 extends UtilResult {

  override def calc: Long = {
    val target = Math.pow(10, 8).toInt

    // f(3)  =       2
    // f(4)  =      12
    // f(5)  =      70
    // f(6)  =     464
    // f(7)  =    3498
    // f(8)  =   29572
    // f(9)  =  277406
    // f(10) = 2863800

    var allSolutions: List[List[Int]] = Nil

    def bruteForce(n: Int): Long = {
      val solution: Array[Int] = Array.fill[Int](n)(-1)
      val yUsed: Array[Boolean] = Array.fill[Boolean](n)(false)
      var solutionCount: Int = 0

      def checkSolution(): Boolean = {
        val seen: mutable.Set[(Int, Int)] = mutable.Set.empty
        val queue: mutable.Queue[(Int, Int)] = mutable.Queue((0, n - 1))
        while (queue.nonEmpty) {
          val coor @ (x, y) = queue.dequeue()
          if (!seen(coor)) {
            if (x == n - 1 && y == 0)
              return true
            seen += coor

            if (x < n - 1 && solution(x + 1) != y)
              queue.enqueue((x + 1, y))
            if (y > 0 && solution(x) != y - 1)
              queue.enqueue((x, y - 1))
          }
        }
        false
      }

      def loop(i: Int): Unit = {
        if (i == n) {
          if (checkSolution()) {
            println(solution.toList)
            allSolutions = solution.toList :: allSolutions
            solutionCount += 1
          }
        } else {
          val lower = if (i < n - 1) 0 else 1
          val upper = if (i == 0) n - 1 else n
          for (k <- lower until upper) {
            if (!yUsed(k)) {
              solution(i) = k
              yUsed(k) = true
              loop(i + 1)
              yUsed(k) = false
            }
          }
        }
      }

      loop(i = 0)
      solutionCount
    }

    val n = 4
    bruteForce(n)

    println("-" * 30)

    var mirrorCount: Int = 0
    val allSolutionsVector = allSolutions.toVector
    val allSolutionsMap = allSolutions.zipWithIndex.toMap
    for (i <- allSolutionsVector.indices) {
      val mirroredSol = allSolutionsVector(i).reverse.map(v => n - 1 - v)
      println(mirroredSol)
      allSolutionsMap.get(mirroredSol) match {
        case Some(value) =>
          if (value != i)
            mirrorCount += 1
          else
            ???
        case None =>
      }

//      for (j <- allSolutionsVector.indices) {
//        if (i != j && allSolutionsVector(j) == mirroredSol)
//          mirrorCount += 1
//      }
    }
    println(s"mirror solutions = $mirrorCount")

    ???
  }

}
