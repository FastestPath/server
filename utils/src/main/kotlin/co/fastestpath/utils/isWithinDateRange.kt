package co.fastestpath.utils

import java.time.Instant

// TODO: test
fun isWithinDateRange(date: Instant, startDate: Instant, endDate: Instant): Boolean {
  if (date == startDate || date == endDate) {
    return true
  }
  return date.isAfter(startDate) && date.isBefore(startDate)
}

