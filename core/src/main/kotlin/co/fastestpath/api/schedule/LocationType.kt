package co.fastestpath.api.schedule

enum class LocationType {
  STATION,
  STATION_STOP,
  OUTSIDE_STOP;

  fun hasParent(): Boolean {
    return this == STATION_STOP
  }
}

