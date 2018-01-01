package co.fastestpath.api.bootstrap.schedule

import co.fastestpath.api.bootstrap.schedule.helpers.StopId
import java.time.ZoneId

class Stop(
  val id: StopId,
  val parentId: StopId?,
  val code: String,
  val name: String,
  val description: String,
  val coordinates: Coordinates,
  val zoneId: String,
  val url: String,
  val locationType: LocationType,
  val timeZone: ZoneId
)
