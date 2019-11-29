package euler

import euler.prob001_100._
import euler.traits.UtilResult

class prob001_050Test extends EulerTestingSuite {
  private val problems: Map[Int, UtilResult] = {
    val problemList =
      List(
        List(Prob001, Prob002, Prob003, Prob004, Prob005),
        List(Prob006, Prob007, Prob008, Prob009, Prob010),
        List(Prob011, Prob012, Prob013, Prob014, Prob015),
        List(Prob016, Prob017, Prob018, Prob019, Prob020),
        List(Prob021, Prob022, Prob023, Prob024, Prob025),
        List(Prob026, Prob027, Prob028, Prob029, Prob030),
        List(Prob031, Prob032, Prob033, Prob034, Prob035),
        List(Prob036, Prob037, Prob038, Prob039, Prob040),
        List(Prob041, Prob042, Prob043, Prob044, Prob045),
        List(Prob046, Prob047, Prob048, Prob049, Prob050)
      )

    problemList.flatten.zipWithIndex.map {
      case (prob, index) => (index + 1, prob)
    }.toMap
  }

  def testProblem(problemNumber: Int, expected: Long): Unit = {
    test("Problem %03d should return %d".format(problemNumber, expected)) {
      val (result, time) = problems(problemNumber).calcWithTime
      println(s"time: $time")
      assert(result == expected)
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

  testProblem(21, 31626L)
  testProblem(22, 871198282L)
  testProblem(23, 4179871L)
  testProblem(24, 2783915460L)
  testProblem(25, 4782L)
  testProblem(26, 983L)
  testProblem(27, -59231L)
  testProblem(28, 669171001L)
  testProblem(29, 9183L)
  testProblem(30, 443839L)

  testProblem(31, 73682L)
  testProblem(32, 45228L)
  testProblem(33, 100L)
  testProblem(34, 40730L)
  testProblem(35, 55L)
  testProblem(36, 872187L)
  testProblem(37, 748317L)
  testProblem(38, 932718654L)
  testProblem(39, 840L)
  testProblem(40, 210L)

  testProblem(41, 7652413L)
  testProblem(42, 162L)
  testProblem(43, 16695334890L)
  testProblem(44, 5482660L)
  testProblem(45, 1533776805L)
  testProblem(46, 5777L)
  testProblem(47, 134043L)
  testProblem(48, 9110846700L)
  testProblem(49, 296962999629L)
  testProblem(50, 997651L)
}
