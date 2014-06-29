package io.lamma.partial.date

import io.lamma._

import scala.annotation.tailrec

private[lamma] trait WeekOps {
  this: Date =>

  /**
   * The first day of this week (Monday) <br>
   *   http://en.wikipedia.org/wiki/ISO_week_date
   */
  @deprecated("replaced with withDayOfWeek(Monday)", "2.1.0")
  def thisWeekBegin = withDayOfWeek(Monday)

  /**
   * The last day of this week (Sunday) <br>
   *   http://en.wikipedia.org/wiki/ISO_week_date
   */
  @deprecated("replaced with withDayOfWeek(Sunday)", "2.1.0")
  def thisWeekEnd = withDayOfWeek(Sunday)

  @deprecated("replaced by daysOfWeek", "2.1.0")
  def thisWeek = daysOfWeek

  /**
   * an iterable for every day in this week <br>
   *   (week starts on Monday and ends on Sunday according to ISO 8601: http://en.wikipedia.org/wiki/ISO_week_date)
   */
  lazy val daysOfWeek = previousOrSame(Monday) to nextOrSame(Sunday)

  /**
   * <b>Java Friendly.</b> It is recommended to use [[daysOfWeek]] for Scala.
   *
   * an iterable for every day in this week <br>
   *   (week starts on Monday and ends on Sunday according to ISO 8601: http://en.wikipedia.org/wiki/ISO_week_date)
   */
  lazy val daysOfWeek4j = daysOfWeek.javaIterable

  lazy val dayOfWeek = JavaDateUtil.dayOfWeek(this)

  /**
   * find the day of this week matching specified day-of-week
   */
  def withDayOfWeek(dow: DayOfWeek) = daysOfWeek.find(_.is(dow)).get

  def is(dow: DayOfWeek) = this.dayOfWeek == dow

  @deprecated("replace by is(Monday)", "2.1.0")
  def isMonday = dayOfWeek == Monday

  @deprecated("replace by is(Tuesday)", "2.1.0")
  def isTuesday = dayOfWeek == Tuesday

  @deprecated("replace by is(Wednesday)", "2.1.0")
  def isWednesday = dayOfWeek == Wednesday

  @deprecated("replace by is(Thursday)", "2.1.0")
  def isThursday = dayOfWeek == Thursday

  @deprecated("replace by is(Friday)", "2.1.0")
  def isFriday = dayOfWeek == Friday

  @deprecated("replace by is(Saturday)", "2.1.0")
  def isSaturday = dayOfWeek == Saturday

  @deprecated("replace by is(Sunday)", "2.1.0")
  def isSunday = dayOfWeek == Sunday

  def isWeekend = is(Saturday) || is(Sunday)

  /**
   * return the first occurrence of the specified day-of-week after current date, unless current date is already on that day.
   *   <br>
   *   For example: <br>
   *     {{{
   *     Date(2014-07-05).nextOrSame(Monday) => Date(2014-07-07)
   *     Date(2014-07-05).nextOrSame(Saturday) => Date(2014-07-5) // note 2014-07-05 itself is already Saturday
   *     }}}
   *    <br>
   */
  def nextOrSame(dow: DayOfWeek) = WeekOps.nextOrSame(this, dow)

  @deprecated(message = "replaced by next(DayOfWeek)", since = "2.1.0")
  def comingDayOfWeek(dow: DayOfWeek) = WeekOps.nextOrSame(this + 1, dow)

  /**
   * return the first occurrence of the specified day-of-week after current date: <br>
   *   <br>
   *   For example: <br>
   *   Date(2014-07-05).next(Monday) => Date(2014-07-07) <br>
   *   Date(2014-07-05).next(Saturday) => Date(2014-07-12) // note 2014-07-05 itself is already Saturday <br>
   */
  def next(dow: DayOfWeek) = WeekOps.nextOrSame(this + 1, dow)

  @deprecated("replaced by next(Monday)", "2.1.0")
  def comingMonday = next(Monday)

  @deprecated("replaced by next(Tuesday)", "2.1.0")
  def comingTuesday = next(Tuesday)

  @deprecated("replaced by next(Wednesday)", "2.1.0")
  def comingWednesday = next(Wednesday)

  @deprecated("replaced by next(Thursday)", "2.1.0")
  def comingThursday = next(Thursday)

  @deprecated("replaced by nextFriday", "2.1.0")
  def comingFriday = next(Friday)

  @deprecated("replaced by nextSaturday", "2.1.0")
  def comingSaturday = next(Saturday)

  @deprecated("replaced by nextSunday", "2.1.0")
  def comingSunday = next(Sunday)

  /**
   * previous day of week before current date, unless current date is already on specified day-of-week <br>
   *
   * For example: <br>
   * {{{
   *  Date(2014-07-05).previousOrSame(Monday) => Date(2014-06-30)
   *  Date(2014-07-05).previousOrSame(Saturday) => Date(2014-07-05) // note 2014-07-05 itself is already Saturday
   * }}}
   */
  def previousOrSame(dow: DayOfWeek) = WeekOps.previousOrSame(this, dow)

  @deprecated("replaced by previous(DayOfWeek)", "2.1.0")
  def pastDayOfWeek(dow: DayOfWeek) = previous(dow)

  /**
   * previous day-of-week excluding this date. For example:
   * {{{
   *   Date(2014-07-05).previous(Monday) => Date(2014-06-30) <br>
   *   Date(2014-07-05).previous(Saturday) => Date(2014-06-28) // note 2014-07-05 itself is already Saturday <br>
   * }}}
   */
  def previous(dow: DayOfWeek) = WeekOps.previousOrSame(this - 1, dow)

  @deprecated("replaced by previous(Monday)", "2.1.0")
  def pastMonday = previous(Monday)

  @deprecated("replaced by previous(Tuesday)", "2.1.0")
  def pastTuesday = previous(Tuesday)

  @deprecated("replaced by previous(Wednesday)", "2.1.0")
  def pastWednesday = previous(Wednesday)

  @deprecated("replaced by previous(Thursday)", "2.1.0")
  def pastThursday = previous(Thursday)

  @deprecated("replaced by previous(Friday)", "2.1.0")
  def pastFriday = previous(Friday)

  @deprecated("replaced by previous(Saturday)", "2.1.0")
  def pastSaturday = previous(Saturday)

  @deprecated("replaced by previous(Sunday)", "2.1.0")
  def pastSunday = previous(Sunday)
}

private object WeekOps {

  @tailrec
  private def nextOrSame(d: Date, target: DayOfWeek): Date = {
    if (d.dayOfWeek == target) {
      d
    } else {
      nextOrSame(d + 1, target)
    }
  }

  @tailrec
  private def previousOrSame(d: Date, target: DayOfWeek): Date = {
    if (d.dayOfWeek == target) {
      d
    } else {
      previousOrSame(d - 1, target)
    }
  }
}