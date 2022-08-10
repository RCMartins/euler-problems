package euler

import euler.prob701_800._
import euler.traits.UtilResult

class prob701_800Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (788, Prob788)
    ).toMap

  testProblem(788, 471745499L, ignored = true)

}
