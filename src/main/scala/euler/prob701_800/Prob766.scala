package euler.prob701_800

import euler.traits.UtilResult

import scala.collection.mutable

/** Created by Ricardo
  */
object Prob766 extends UtilResult {

  private case class Coor(x: Int, y: Int) {

    def +(other: Coor): Coor = Coor(x + other.x, y + other.y)

  }

  private case class Block(pieces: Set[Coor]) {

    def map(f: Coor => Coor): Block = Block(pieces.map(f))

  }

  private val allDir: List[Coor] =
    List(Coor(1, 0), Coor(0, 1), Coor(-1, 0), Coor(0, -1))

  private def slideIt(
      boardSize: (Int, Int),
      initialBlocks: Set[Block],
      initialEmpty: Set[Coor]
  ): Int = {
    val (maxX, maxY) =
      boardSize
    val configurations: mutable.Set[Set[Block]] =
      mutable.Set.empty
    val queue: mutable.Queue[(Set[Block], Set[Coor])] =
      mutable.Queue((initialBlocks, initialEmpty))

    while (queue.nonEmpty) {
      val (blocksSet, emptySet) = queue.dequeue()
      if (!configurations(blocksSet)) {
        configurations += blocksSet

        val possibleMovableBlocks: Set[Block] =
          emptySet.flatMap { empty =>
            blocksSet.filter(_.pieces.exists(piece => allDir.exists(_ + piece == empty)))
          }

        possibleMovableBlocks.foreach { block =>
          allDir.foreach { dir =>
            val newBlockPosition: Block = block.map(_ + dir)
            val validPosition: Boolean =
              newBlockPosition.pieces.forall(piece =>
                piece.x >= 0 && piece.x < maxX && piece.y >= 0 && piece.y < maxY &&
                  (block.pieces(piece) || emptySet(piece))
              )

            if (validPosition) {
              val newBlocks: Set[Block] =
                blocksSet - block + newBlockPosition
              val newEmptySet: Set[Coor] =
                emptySet ++ block.pieces -- newBlockPosition.pieces

              queue.addOne((newBlocks, newEmptySet))
            }
          }
        }
      }
    }

    configurations.size
  }

  private def testConfiguration: Long = {
    val boardSize: (Int, Int) = (4, 3)
    val initialBlocks: Set[Block] =
      Set(
        Block(Set(Coor(0, 0), Coor(1, 0), Coor(0, 1))),
        Block(Set(Coor(2, 0))),
        Block(Set(Coor(1, 1))),
        Block(Set(Coor(2, 1))),
        Block(Set(Coor(0, 2))),
        Block(Set(Coor(1, 2))),
        Block(Set(Coor(2, 2))),
        Block(Set(Coor(3, 2))),
      )

    val empty: Set[Coor] =
      Set(Coor(3, 0), Coor(3, 1))

    slideIt(boardSize, initialBlocks, empty)
  }

  private def problemConfiguration: Long = {
    val boardSize: (Int, Int) = (6, 5)
    val initialBlocks: Set[Block] =
      Set(
        Block(Set(Coor(1, 0), Coor(2, 0), Coor(1, 1))),
        Block(Set(Coor(3, 0), Coor(2, 1), Coor(3, 1))),
        Block(Set(Coor(4, 0), Coor(5, 0), Coor(4, 1))),
        Block(Set(Coor(5, 1), Coor(5, 2))),
        Block(Set(Coor(4, 2), Coor(4, 3))),
        Block(Set(Coor(2, 2), Coor(3, 2), Coor(2, 3), Coor(3, 3))),
        Block(Set(Coor(5, 3), Coor(4, 4), Coor(5, 4))),
        Block(Set(Coor(2, 4), Coor(3, 4))),
        Block(Set(Coor(0, 2))),
        Block(Set(Coor(0, 3))),
        Block(Set(Coor(0, 4))),
        Block(Set(Coor(1, 2))),
        Block(Set(Coor(1, 3))),
        Block(Set(Coor(1, 4))),
      )

    val empty: Set[Coor] =
      Set(Coor(0, 0), Coor(0, 1))

    slideIt(boardSize, initialBlocks, empty)
  }

  override def calc: Long = {
    problemConfiguration

    // answer: 2613742
  }

}
