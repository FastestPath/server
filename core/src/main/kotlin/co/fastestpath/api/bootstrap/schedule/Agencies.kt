package co.fastestpath.api.bootstrap.schedule

import co.fastestpath.gtfs.GtfsAgency
import java.time.ZoneId

fun createAgencies(entities: List<GtfsAgency>): Agencies {

  require(!entities.isEmpty()) { "At least one agency is required." }

  val map = entities.associateBy(
    { AgencyId.of(it.id) }, {
    Agency(
      id = AgencyId.of(it.id),
      name = it.name,
      url = it.url,
      timeZone = ZoneId.of(it.timezone),
      language = it.language,
      phone = it.phone,
      fareUrl = it.fareUrl,
      email = it.email
    )
  })

  return Agencies(map, map.values.first().timeZone)
}

class Agency(
  val id: AgencyId,
  val name: String,
  val url: String,
  val timeZone: ZoneId,
  val language: String,
  val phone: String,
  val fareUrl: String,
  val email: String
)

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

class Agencies(val map: Map<AgencyId, Agency>, val timeZone: ZoneId)

