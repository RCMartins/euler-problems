package euler

import euler.prob001_100._
import euler.traits.UtilResult

class prob051_100Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] = {
    val problemList =
      List(
        List(Prob051, Prob052, Prob053, Prob054, Prob055),
        List(Prob056, Prob057, Prob058, Prob059, Prob060),
        List(Prob061, Prob062, Prob063)
      )

    problemList.flatten.zipWithIndex.map { case (prob, index) =>
      (index + 51, prob)
    }.toMap ++
      Map(
        65 -> Prob065,
        67 -> Prob067,
        68 -> Prob068,
        72 -> Prob072,
        96 -> Prob096,
        98 -> Prob098,
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

  testProblem(61, 28684L)
  testProblem(62, 127035954683L)
  testProblem(63, 49L)
  testProblem(65, 272L)
  testProblem(67, 7273L)
  testProblem(68, 6531031914842725L)

  testProblem(72, 303963552391L, ignored = true)

  testProblem(96, 24702L)
  testProblem(98, 18769L)
}
