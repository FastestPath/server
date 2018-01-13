package co.fastestpath.api.schedule

import co.fastestpath.gtfs.GtfsTimePointType

enum class TimePointType {
  APPROXIMATE,
  EXACT;

  companion object {
    fun of(value: GtfsTimePointType?) = if (value == GtfsTimePointType.EXACT) EXACT else APPROXIMATE
  }
}
