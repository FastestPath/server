package co.fastestpath.api.schedule

data class RouteId private constructor(private val value: String) {
  companion object {
    private val cache: MutableMap<String, RouteId> = HashMap()

    fun of(value: String): RouteId {
      var routeId = cache[value]
      if (routeId != null) {
        return routeId
      }
      routeId = RouteId(value)
      cache[value] = routeId
      return routeId
    }
  }
}

