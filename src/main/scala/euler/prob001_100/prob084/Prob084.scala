package euler.prob001_100.prob084

import euler.traits.UtilResult

import scala.util.Random

/**
  * Created by Ricardo
  */
object Prob084 extends UtilResult {
  /*
  GO  A1  CC1 A2  T1  R1  B1  CH1 B2  B3  JAIL
  H2                                      C1
  T2                                      U1
  H1                                      C2
  CH3                                     C3
  R4                                      R2
  G3                                      D1
  CC3                                     CC2
  G2                                      D2
  G1                                      D3
  G2J F3  U2  F2  F1  R3  E3  E2  CH2 E1  FP
   */

  val l1 @ List(go, a1, cc1, a2, t1, r1, b1, ch1, b2, b3) = (0 until 10).toList
  val l2 @ List(jail, c1, u1, c2, c3, r2, d1, cc2, d2, d3) = (10 until 20).toList
  val l3 @ List(fp, e1, ch2, e2, e3, r3, f1, f2, u2, f3) = (20 until 30).toList
  val l4 @ List(g2j, g1, g2, cc3, g3, r4, ch3, h1, t2, h2) = (30 until 40).toList
  val board: List[Int] = l1 ++ l2 ++ l3 ++ l4
  val railways: List[Int] = List(r1, r2, r3, r4)
  val utilities: List[Int] = List(u1, u2)
  val communities: List[Int] = List(cc1, cc2, cc3)
  val changePos: List[Int] = List(ch1, ch2, ch3)

  val DiceSize = 4

  override def calc: Long = {
    val turns = 25000000
    val sim = simulate(Turn(0, 0, Vector.fill(40)(0)), turns)
    println(sim)
    val ordered = sim.counts.zipWithIndex.sortBy(-_._1)
    println(ordered)
    ordered.take(3).map(_._2).map("%02d".format(_)).mkString.toLong //101524
  }

  def simulate(turn: Turn, turnsLeft: Int): Turn = {
    if (turnsLeft == 0)
      turn
    else
      simulate(simulate(turn), turnsLeft - 1)
  }

  def simulate(turn: Turn): Turn = {
    val (diceValues, isDouble) = monopolyDices
    val newPos = (turn.position + diceValues) % 40
    val finalPos =
      newPos match {
        case `g2j`                 => jail
        case `cc1` | `cc2` | `cc3` => communityChest(newPos)
        case `ch1` | `ch2` | `ch3` => chanceChest(newPos)
        case _                     => newPos
      }

    if (isDouble) {
      if (turn.doubles == 2)
        Turn(jail, 0, turn.counts.updated(jail, turn.counts(jail) + 1))
      else
        Turn(finalPos, turn.doubles + 1, turn.counts.updated(finalPos, turn.counts(finalPos) + 1))
    } else {
      Turn(finalPos, 0, turn.counts.updated(finalPos, turn.counts(finalPos) + 1))
    }
  }

  def communityChest(position: Int): Int = {
    Random.nextInt(16) match {
      case 0 => go
      case 1 => jail
      case _ => position
    }
  }

  def chanceChest(position: Int): Int = {
    Random.nextInt(16) match {
      case 0 => go
      case 1 => jail
      case 2 => c1
      case 3 => e3
      case 4 => h2
      case 5 => r1
      case 6 => next(position, railways)
      case 7 => next(position, railways)
      case 8 => next(position, utilities)
      case 9 => if (position == ch3) communityChest(position - 3) else position - 3
      case _ => position
    }
  }

  def next(position: Int, list: List[Int]): Int = {
    val pos = (position + 1) % 40
    if (list.contains(pos))
      pos
    else
      next(pos, list)
  }

  def dice(diceSize: Int = DiceSize): Int = Random.nextInt(diceSize) + 1

  def twoDice(diceSize: Int = DiceSize): Int = dice(diceSize) + dice(diceSize)

  def monopolyDices: (Int, Boolean) = {
    val dice1 = dice()
    val dice2 = dice()
    (dice1 + dice2, dice1 == dice2)
  }

  case class Turn(position: Int, doubles: Int, counts: Vector[Int])
}
