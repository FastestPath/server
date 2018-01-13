package co.fastestpath.api.schedule.helpers

import co.fastestpath.api.schedule.Coordinates
import co.fastestpath.api.schedule.LocationType
import co.fastestpath.api.schedule.Stop
import co.fastestpath.api.schedule.StopId
import co.fastestpath.gtfs.GtfsLocationType
import co.fastestpath.gtfs.GtfsStop
import org.apache.commons.lang3.StringUtils
import java.time.ZoneId

fun createStops(entities: List<GtfsStop>): Map<StopId, Stop> {

  val entityMap: Map<StopId, GtfsStop> = entities.associateBy({ StopId.of(it.id) }, { it })

  return entities.associateBy({ StopId.of(it.id) }, {

    val locationType = determineLocationType(it, entityMap)
    val parentId = if (locationType.hasParent()) StopId.of(it.parentStation) else null
    val coordinates = Coordinates(it.lat, it.lon)

    Stop(
      id = StopId.of(it.id),
      parentId = parentId,
      code = it.code,
      name = it.name,
      description = it.desc,
      coordinates = coordinates,
      zoneId = it.zoneId,
      url = it.url,
      locationType = locationType,
      timeZone = ZoneId.of(it.timeZone)
    )
  })
}

/**
 * Determines if a stop is:
 *  - {@link LocationType.STATION} a station
 *  - {@link LocationType.STATION_STOP} a stop within a station
 *  - {@link LocationType.OUTSIDE_STOP} a stop not part of a station
 */
private fun determineLocationType(stop: GtfsStop, entityMap: Map<StopId, GtfsStop>): LocationType {
  if (stop.locationType == GtfsLocationType.STATION) {
    return LocationType.STATION
  }
  return if (isStationStop(stop, entityMap)) LocationType.STATION_STOP else LocationType.OUTSIDE_STOP
}

/**
 * A stop belongs to a station only if its parent stop is marked as a station.
 */
private fun isStationStop(stop: GtfsStop, entityMap: Map<StopId, GtfsStop>): Boolean {
  val parentStation = stop.parentStation
  if (StringUtils.isBlank(parentStation)) {
    return false
  }

  val parentId = StopId.of(parentStation)
  val parentEntity = entityMap[parentId]
  return parentEntity?.locationType == GtfsLocationType.STATION
}
