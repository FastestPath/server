package co.fastestpath.api.schedule

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
