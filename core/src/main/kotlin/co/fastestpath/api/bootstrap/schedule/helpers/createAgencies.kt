package co.fastestpath.api.bootstrap.schedule.helpers

import co.fastestpath.api.bootstrap.schedule.Agency
import co.fastestpath.api.bootstrap.schedule.AgencyId
import co.fastestpath.gtfs.GtfsAgency
import java.time.ZoneId

fun createAgencies(agencies: List<GtfsAgency>): Map<AgencyId, Agency> {

  require(!agencies.isEmpty()) { "At least one agency is required." }

  return agencies.associateBy(
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
}
