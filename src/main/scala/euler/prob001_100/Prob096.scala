package euler.prob001_100

import euler.traits.UtilResult

import scala.collection.immutable.SortedSet

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
      if (possibleNumbers != updatedPossibleNumbers) {
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
    lazy val boxOthercolumn: Seq[SmartSquare] = box.filterNot(column3.contains)

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

        square.numbers.foreach {
          _.find(number => square.boxOtherLine.forall(!_.allNumbers(number))) match {
            case None =>
            case Some(numberToRemove) =>
              square.lineOther6.foreach(_.removeNumber(numberToRemove))
          }
        }

        square.numbers.foreach {
          _.find(number => square.boxOthercolumn.forall(!_.allNumbers(number))) match {
            case None =>
            case Some(numberToRemove) =>
              square.columnOther6.foreach(_.removeNumber(numberToRemove))
          }
        }

        square.numbers.foreach { numbers =>
          val allEqual = square.boxWithoutSelf.filter(_.allNumbers == numbers)
          if (allEqual.sizeIs == numbers.size - 1) {
            square.boxWithoutSelf.filterNot(allEqual.contains).foreach(_.removeNumber(numbers))
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

  private def initialUpdate(game: Seq[Seq[SmartSquare]]): Unit =
    game.foreach(_.foreach(_.update()))

  def calc: Long = {
    val data: List[String] = readData("p096_sudoku.txt").split("\n").toList
    val games: List[List[String]] = data.grouped(10).toList

    val gameResults: List[Int] =
      games.zipWithIndex.drop(6).map { case (gameStrList, gameIndex) =>
        println(s" >>> Game ${gameIndex + 1} <<<")
        println()

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

        drawGame(game)

        addGroupRules(game)
        addGroupRules(columns)
        addGroupRules(boxes)

        initialUpdate(game)

        drawGame(game)

        val result = game.head.take(3).flatMap(_.number)
        if (result.sizeIs == 3)
          result.mkString.toInt
        else
          ???
      }

    gameResults.sum
  }

}
