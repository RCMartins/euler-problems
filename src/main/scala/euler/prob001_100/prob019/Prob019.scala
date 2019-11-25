package euler.prob001_100.prob019

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob019 extends UtilResult {
  def calc: Long = {
    val sizes = Vector(0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    var day = 0
    var month = 1
    var year = 1900
    var weekDay = 0
    var sundays = 0

    while (year < 2001) {
      if (month == 2) {
        if (year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0))
          day = (day + 1) % (29 + 1)
        else
          day = (day + 1) % (28 + 1)
      } else {
        day = (day + 1) % (sizes(month) + 1)
      }
      if (day == 0) {
        day = 1
        month = (month + 1) % 13
        if (month == 0) {
          month += 1
          year += 1
        }
      }

      weekDay = (weekDay + 1) % 7
      if (year >= 1901 && weekDay == 0 && day == 1)
        sundays += 1

      println(day, month, year, weekDay)
    }

    sundays
  }
}
