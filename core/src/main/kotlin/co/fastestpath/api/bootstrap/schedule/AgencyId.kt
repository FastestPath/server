package co.fastestpath.api.bootstrap.schedule

data class AgencyId private constructor(val value: String) {
  companion object {
    private val cache: MutableMap<String, AgencyId> = HashMap()

    fun of(value: String): AgencyId {
      var agencyId = cache[value]
      if (agencyId != null) {
        return agencyId
      }
      agencyId = AgencyId(value)
      cache[value] = agencyId
      return agencyId
    }
  }
}
