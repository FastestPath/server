package co.fastestpath.utils

import java.text.SimpleDateFormat
import java.time.Instant

data class SimpleDate(private val value: String) : Comparable<SimpleDate> {

  companion object {
    val FORMAT = SimpleDateFormat("yyyyMMdd")
  }

  private val instant: Instant

  init {
    this.instant = FORMAT.parse(value).toInstant()
  }

  override fun compareTo(other: SimpleDate) = instant.compareTo(other.instant)

  fun isWithinRange(startDate: SimpleDate, endDate: SimpleDate): Boolean {
    if (this == startDate || this == endDate) {
      return true
    }
    return instant.isAfter(startDate.instant) && instant.isBefore(endDate.instant)
  }
}
