package co.fastestpath.api.bootstrap.schedule

data class StopId private constructor(val value: String) {
  companion object {

    private val cache: MutableMap<String, StopId> = HashMap()

    fun of(value: String): StopId {
      var stopId = cache[value]
      if (stopId != null) {
        return stopId
      }
      stopId = StopId(value)
      cache[value] = stopId
      return stopId
    }
  }
}
