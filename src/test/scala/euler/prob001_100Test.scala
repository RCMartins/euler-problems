package euler

import euler.prob001_100._
import euler.traits.UtilResult
import org.scalatest.FunSuite

class prob001_100Test extends FunSuite {
  private val problems: Map[Int, UtilResult] = {
    val problemList =
      List(
        List(Prob001, Prob002, Prob003, Prob004, Prob005),
        List(Prob006, Prob007, Prob008, Prob009, Prob010),
        List(Prob011, Prob012, Prob013, Prob014, Prob015),
        List(Prob016, Prob017, Prob018, Prob019, Prob020)
      )

    problemList.flatten.zipWithIndex.map {
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

  testProblem(11, 70600674L)
  testProblem(12, 76576500L)
  testProblem(13, 5537376230L)
  testProblem(14, 837799L)
  testProblem(15, 137846528820L)
  testProblem(16, 1366L)
  testProblem(17, 21124L)
  testProblem(18, 1074L)
  testProblem(19, 171L)
  testProblem(20, 648L)
}
