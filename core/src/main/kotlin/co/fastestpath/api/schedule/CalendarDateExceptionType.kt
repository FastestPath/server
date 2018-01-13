package co.fastestpath.api.schedule

import co.fastestpath.gtfs.GtfsCalendarDateExceptionType

enum class CalendarDateExceptionType {
  ADDED,
  REMOVED;

  companion object {
    fun of(value: GtfsCalendarDateExceptionType) = if (value == GtfsCalendarDateExceptionType.ADDED) ADDED else REMOVED
  }
}
