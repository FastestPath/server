package co.fastestpath.api.bootstrap.schedule

enum class LocationType {
  STATION,
  STATION_STOP,
  OUTSIDE_STOP;

  fun hasParent(): Boolean {
    return this == STATION_STOP
  }
}

