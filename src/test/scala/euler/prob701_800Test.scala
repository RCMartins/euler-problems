package euler

import euler.prob701_800._
import euler.traits.UtilResult

class prob701_800Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (719, Prob719),
      (751, Prob751),
      (766, Prob788),
      (788, Prob788),
    ).toMap

  testProblem(719, 128088830547982L, ignored = true)

  testProblemDecimal(751, BigDecimal("2.223561019313554106173177"))

  testProblem(766, 2613742L, ignored = true)

  testProblem(788, 471745499L, ignored = true)

}
