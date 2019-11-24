package euler

import euler.prob001_100.prob001.Prob001
import euler.prob001_100.prob002.Prob002
import euler.prob001_100.prob003.Prob003
import euler.prob001_100.prob004.Prob004
import euler.prob001_100.prob005.Prob005
import euler.prob001_100.prob006.Prob006
import euler.prob001_100.prob007.Prob007
import euler.prob001_100.prob008.Prob008
import euler.prob001_100.prob009.Prob009
import euler.prob001_100.prob010.Prob010
import euler.traits.UtilResult
import org.scalatest.FunSuite

class prob001_100Test extends FunSuite {
  private val problems: Map[Int, UtilResult] = {
    val problemList =
      List(Prob001, Prob002, Prob003, Prob004, Prob005, Prob006, Prob007, Prob008, Prob009, Prob010)

    problemList.zipWithIndex.map {
      case (prob, index) => (index + 1, prob)
    }.toMap
  }

  def testProblem(problemNumber: Int, expected: Long): Unit = {
    test("Problem %03d".format(problemNumber)) {
      assert(problems(problemNumber).calc == expected)
    }
  }

  testProblem(1, 233168L)
  testProblem(2, 4613732L)
  testProblem(3, 6857L)
  testProblem(4, 906609L)
  testProblem(5, 232792560L)
  testProblem(6, 25164150L)
  testProblem(7, 104743)
  testProblem(8, 23514624000L)
  testProblem(9, 31875000L)
  testProblem(10, 142913828922L)
}
