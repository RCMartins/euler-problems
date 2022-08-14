package euler.prob001_100

import euler.traits.UtilResult

import scala.annotation.tailrec
import scala.collection.immutable.SortedSet
import scala.util.{Failure, Success, Try}

/** Created by Ricardo
  */
object Prob096 extends UtilResult {

  private class SmartSquare(initialNumber: Int) {

    private var line: Seq[SmartSquare] = Vector.empty
    private var column: Seq[SmartSquare] = Vector.empty
    private var box: Seq[SmartSquare] = Vector.empty

    private var possibleNumbers: SortedSet[Int] =
      if (initialNumber == 0) SortedSet.from(1 to 9) else SortedSet(initialNumber)

    private var references: List[() => Unit] = Nil

    def number: Option[Int] =
      if (possibleNumbers.sizeIs == 1) possibleNumbers.headOption else None

    def numbers: Option[SortedSet[Int]] =
      if (possibleNumbers.sizeIs == 1) None else Some(possibleNumbers)

    def allNumbers: SortedSet[Int] =
      possibleNumbers

    def backupSet(backupNumbers: SortedSet[Int]): Unit =
      possibleNumbers = backupNumbers

    def setNumber(newNumber: Int): Unit = {
      val updatedPossibleNumbers = SortedSet(newNumber)
      if (possibleNumbers != updatedPossibleNumbers) {
        possibleNumbers = updatedPossibleNumbers
        update()
      }
    }

    def removeNumber(numberToRemove: Int): Unit =
      removeNumber(List(numberToRemove))

    def removeNumber(numbersToRemove: Iterable[Int]): Unit = {
      val updatedPossibleNumbers = possibleNumbers -- numbersToRemove
      if (updatedPossibleNumbers.isEmpty)
        throw new Exception("Reached an impossible state!")
      else if (possibleNumbers != updatedPossibleNumbers) {
        possibleNumbers = updatedPossibleNumbers
        update()
      }
    }

    def runIfUnset(f: SmartSquare => Any): Unit =
      if (possibleNumbers.sizeIs > 1) f(this)

    def update(): Unit =
      references.foreach(_())

    def addReference(newReference: () => Unit): Unit =
      references = newReference :: references

    def getLine: Seq[SmartSquare] = line

    def setLine(seq: Seq[SmartSquare]): Unit = line = seq

    def getColumn: Seq[SmartSquare] = column

    def setColumn(seq: Seq[SmartSquare]): Unit = column = seq

    def getBox: Seq[SmartSquare] = box

    def setBox(seq: Seq[SmartSquare]): Unit = box = seq

    lazy val boxWithoutSelf: Seq[SmartSquare] = box.filterNot(_ eq this)

    lazy val line3: Seq[SmartSquare] = line.filter(box.contains)
    lazy val lineOther6: Seq[SmartSquare] = line.filterNot(box.contains)
    lazy val boxOtherLine: Seq[SmartSquare] = box.filterNot(line3.contains)

    lazy val column3: Seq[SmartSquare] = column.filter(box.contains)
    lazy val columnOther6: Seq[SmartSquare] = column.filterNot(box.contains)
    lazy val boxOtherColumn: Seq[SmartSquare] = box.filterNot(column3.contains)

    lazy val allLineBoxes: Seq[Seq[SmartSquare]] = line.map(_.getBox).distinct
    lazy val allColumnBoxes: Seq[Seq[SmartSquare]] = column.map(_.getBox).distinct

    lazy val otherLineBoxes: Seq[Seq[SmartSquare]] = allLineBoxes.filterNot(_ eq box)
    lazy val otherColumnBoxes: Seq[Seq[SmartSquare]] = allColumnBoxes.filterNot(_ eq box)

    override def toString: String =
      if (possibleNumbers.sizeIs == 1)
        s"     ${possibleNumbers.head}     "
      else {
        val nStr: String = possibleNumbers.mkString("[", "", "]")
        val after = (11 - nStr.length) / 2
        val before = 11 - nStr.length - after
        (" " * before) + nStr + (" " * after)
      }

    override def equals(obj: Any): Boolean = this eq obj.asInstanceOf[SmartSquare]

  }

  private def drawGame(game: Seq[Seq[SmartSquare]]): Unit = {
    val size = 9 * 12 + 7
    println(
      game
        .map {
          _.map(_.toString).grouped(3).map(_.mkString(" ")).mkString("| ", " | ", " |\n")
        }
        .grouped(3)
        .map(_.mkString)
        .mkString("-" * size + "\n", "-" * size + "\n", "-" * size + "\n")
    )
    println()
  }

  private def addRule(square: SmartSquare, groupWithoutSelf: Seq[SmartSquare]): Unit =
    groupWithoutSelf.foreach {
      _.runIfUnset(_.addReference { () =>
        square.removeNumber(groupWithoutSelf.flatMap(_.number))

        val otherPossibleNumbers: Set[Int] = groupWithoutSelf.flatMap(_.allNumbers).toSet
        square.numbers.foreach {
          _.find(!otherPossibleNumbers(_)) match {
            case Some(number) =>
              square.setNumber(number)
            case None =>
          }
        }
      })
    }

  private def addGroupRules(groupSeq: Seq[Seq[SmartSquare]]): Unit =
    groupSeq.foreach { group =>
      group.foreach { square =>
        val lineWithoutSelf = group.filterNot(_ eq square)
        addRule(square, lineWithoutSelf)
      }
    }

  private def addBoxRules(game: Seq[Seq[SmartSquare]]): Unit = {
    game.flatten.foreach { square =>
      square.getBox.foreach(_.runIfUnset(_.addReference { () =>
        square.numbers.foreach {
          _.filter(number => square.boxOtherLine.forall(!_.allNumbers(number))).foreach {
            numberToRemove => square.lineOther6.foreach(_.removeNumber(numberToRemove))
          }
        }

        square.numbers.foreach {
          _.filter(number => square.boxOtherColumn.forall(!_.allNumbers(number))).foreach {
            numberToRemove => square.columnOther6.foreach(_.removeNumber(numberToRemove))
          }
        }

        square.numbers.foreach { numbers =>
          val allEqual = square.boxWithoutSelf.filter(_.allNumbers == numbers)
          if (allEqual.sizeIs == numbers.size - 1) {
            square.boxWithoutSelf.filterNot(allEqual.contains).foreach(_.removeNumber(numbers))
          }
        }
      }))

      square.allLineBoxes.flatten.foreach(_.runIfUnset(_.addReference { () =>
        square.numbers.foreach { numbers =>
          numbers.foreach { number =>
            if (square.boxOtherColumn.count(_.allNumbers(number)) == 0) {
              val filteredSquares: Seq[SmartSquare] =
                square.column3.filter(_.numbers.exists(_(number)))
              lazy val otherFilteredBox: Seq[Seq[Seq[SmartSquare]]] =
                square.otherLineBoxes
                  .filter(!_.exists(_.number.contains(number)))
                  .map(_.map(_.line3).distinct.filter(_.exists(_.numbers.exists(_(number)))))
                  .filter(_.nonEmpty)

              if (filteredSquares.sizeIs == 2 && square.column3.forall(!_.number.contains(number)))
                otherFilteredBox match {
                  case Seq(first, other)
                      if first.size == 2 &&
                        filteredSquares.map(_.getLine) == first.map(_.head.getLine) =>
                    val excludeLines = filteredSquares.flatMap(_.getLine)
                    other.flatten.filter(excludeLines.contains).foreach(_.removeNumber(number))
                  case Seq(other, second)
                      if second.size == 2 &&
                        filteredSquares.map(_.getLine) == second.map(_.head.getLine) =>
                    val excludeLines = filteredSquares.flatMap(_.getLine)
                    other.flatten.filter(excludeLines.contains).foreach(_.removeNumber(number))
                  case _ =>
                }
            }
          }
        }
      }))

      square.allColumnBoxes.flatten.foreach(_.runIfUnset(_.addReference { () =>
        square.numbers.foreach { numbers =>
          numbers.foreach { number =>
            if (square.boxOtherLine.count(_.allNumbers(number)) == 0) {
              val filteredSquares: Seq[SmartSquare] =
                square.line3.filter(_.numbers.exists(_(number)))
              lazy val otherFilteredBox: Seq[Seq[Seq[SmartSquare]]] =
                square.otherColumnBoxes
                  .filter(!_.exists(_.number.contains(number)))
                  .map(_.map(_.column3).distinct.filter(_.exists(_.numbers.exists(_(number)))))
                  .filter(_.nonEmpty)

              if (filteredSquares.sizeIs == 2 && square.line3.forall(!_.number.contains(number)))
                otherFilteredBox match {
                  case Seq(first, other)
                      if first.size == 2 &&
                        filteredSquares.map(_.getColumn) == first.map(_.head.getColumn) =>
                    val excludeColumns = filteredSquares.flatMap(_.getColumn)
                    other.flatten.filter(excludeColumns.contains).foreach(_.removeNumber(number))
                  case Seq(other, second)
                      if second.size == 2 &&
                        filteredSquares.map(_.getColumn) == second.map(_.head.getColumn) =>
                    val excludeColumns = filteredSquares.flatMap(_.getColumn)
                    other.flatten.filter(excludeColumns.contains).foreach(_.removeNumber(number))
                  case _ =>
                }
            }
          }
        }
      }))
    }
  }

  private def initialUpdate(game: Seq[Seq[SmartSquare]]): Unit =
    game.foreach(_.foreach(_.update()))

  private def justSolveIt(game: Seq[Seq[SmartSquare]]): Int = {
    drawGame(game)

    def createBackup(): Seq[Seq[SortedSet[Int]]] =
      game.map(_.map(_.allNumbers))

    def createSmartSquaresToTry(): List[(SmartSquare, List[Int])] =
      game
        .flatMap(_.filter(_.number.isEmpty))
        .toList
        .map(smart => (smart, smart.allNumbers.toList))

    def reloadBackup(backup: Seq[Seq[SortedSet[Int]]]): Unit =
      game.zip(backup).foreach { case (line, backupLine) =>
        line.zip(backupLine).foreach { case (square, backupValue) =>
          square.backupSet(backupValue)
        }
      }

    @tailrec
    def loop(
        allSmartSquares: List[(SmartSquare, List[Int])],
        backup: Seq[Seq[SortedSet[Int]]],
        tries: Int
    ): Int =
      allSmartSquares match {
        case Nil =>
          println("Can't solve it :(")
          0
        case (_, Nil) :: next =>
          loop(next, backup, tries)
        case (smartSquare, number :: nextNumbers) :: next =>
          val result =
            Try {
              smartSquare.setNumber(number)
            }
          result match {
            case Success(_) =>
              val result = game.head.take(3).flatMap(_.number)
              if (result.sizeIs == 3) {
                println(s"Solved it in $tries tries!")
                drawGame(game)
                result.mkString.toInt
              } else {
                reloadBackup(backup)
                loop((smartSquare, nextNumbers) :: next, backup, tries + 1)
              }
            case Failure(_) =>
              reloadBackup(backup)
              smartSquare.removeNumber(number)

              val result = game.head.take(3).flatMap(_.number)
              if (result.sizeIs == 3) {
                println(s"Solved it in $tries tries!")
                drawGame(game)
                result.mkString.toInt
              } else
                loop(createSmartSquaresToTry(), createBackup(), tries + 1)
          }
      }

    loop(
      createSmartSquaresToTry(),
      createBackup(),
      tries = 1
    )
  }

  def calc: Long = {
    val data: List[String] = readData("p096_sudoku.txt").split("\n").toList
    val games: List[List[String]] = data.grouped(10).toList

    val gameResults: List[Int] =
      games.zipWithIndex.map { case (gameStrList, gameIndex) =>
        val gameRaw: Seq[Seq[Int]] =
          gameStrList.drop(1).toVector.map(_.toCharArray.map(_.toInt - '0').toVector)

        val game: Seq[Seq[SmartSquare]] =
          gameRaw.map(_.map(new SmartSquare(_)))

        val columns: Seq[Seq[SmartSquare]] =
          game.transpose

        val boxes: Seq[Seq[SmartSquare]] =
          game
            .grouped(3)
            .flatMap { lines =>
              lines.transpose.grouped(3).map(_.flatten)
            }
            .toSeq

        game.foreach(line => line.foreach(_.setLine(line)))
        columns.foreach(column => column.foreach(_.setColumn(column)))
        boxes.foreach(box => box.foreach(_.setBox(box)))

        addGroupRules(game)
        addGroupRules(columns)
        addGroupRules(boxes)
        addBoxRules(game)

        initialUpdate(game)

        val result = game.head.take(3).flatMap(_.number)
        if (result.sizeIs == 3)
          result.mkString.toInt
        else {
          println(s" >>> Game ${gameIndex + 1} <<<")
          println()
          justSolveIt(game)
        }
      }

    println(s"${gameResults.count(_ == 0)} puzzles left to solve!")

    gameResults.sum

    // anwser: 24702
  }

}
