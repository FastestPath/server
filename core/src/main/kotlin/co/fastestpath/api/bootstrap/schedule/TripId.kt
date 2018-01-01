package co.fastestpath.api.bootstrap.schedule

data class TripId private constructor(val value: String) {
  companion object {

    private val cache: MutableMap<String, TripId> = HashMap()

    fun of(value: String): TripId {
      var tripId = cache[value]
      if (tripId != null) {
        return tripId
      }
      tripId = TripId(value)
      cache[value] = tripId
      return tripId
    }
  }
}
