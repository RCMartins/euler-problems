package euler.testStuff

import scala.util.{Random, Success, Try}

object SquareGuess {

  def main(args: Array[String]): Unit = {

    val all = Random.shuffle((11 to 99).map(x => x * x))

    all.foreach {
      square =>

        println(s"NUMBER: $square")

        var correct = false
        do {
          val input = scala.io.StdIn.readLine()

          Try(input.toInt) match {
            case Success(value) if value * value == square =>
              correct = true
            case _ =>
          }
        } while (!correct)
    }

  }
}
