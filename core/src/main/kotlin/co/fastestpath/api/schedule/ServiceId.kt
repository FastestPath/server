package co.fastestpath.api.schedule

data class ServiceId private constructor(val value: String) {
  companion object {

    private val cache: MutableMap<String, ServiceId> = HashMap()

    fun of(value: String): ServiceId {
      var serviceId = cache[value]
      if (serviceId != null) {
        return serviceId
      }
      serviceId = ServiceId(value)
      cache[value] = serviceId
      return serviceId
    }
  }
}
