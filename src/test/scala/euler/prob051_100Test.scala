package euler

import euler.prob001_100._
import euler.traits.UtilResult

class prob051_100Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] = {
    val problemList =
      List(
        List(Prob051, Prob052, Prob053, Prob054, Prob055),
        List(Prob056, Prob057, Prob058, Prob059, Prob060)
      )

    problemList.flatten.zipWithIndex.map { case (prob, index) =>
      (index + 51, prob)
    }.toMap ++
      Map(
        72 -> Prob072,
        96 -> Prob096,
      )
  }

  testProblem(51, 121313L)
  testProblem(52, 142857L)
  testProblem(53, 4075L)
  testProblem(54, 376L)
  testProblem(55, 249L)
  testProblem(56, 972L)
  testProblem(57, 153L)
  testProblem(58, 26241L)
  testProblem(59, 129448L)
  testProblem(60, 26033L, ignored = true)

  testProblem(72, 303963552391L, ignored = true)
  testProblem(96, 24702L)
}
