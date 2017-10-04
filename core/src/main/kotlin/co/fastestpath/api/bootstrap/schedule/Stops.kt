package co.fastestpath.api.bootstrap.schedule

import co.fastestpath.gtfs.GtfsLocationType
import co.fastestpath.gtfs.GtfsStop
import org.apache.commons.lang3.StringUtils
import java.time.ZoneId

fun createStops(entities: List<GtfsStop>): Stops {

  require(!entities.isEmpty()) { "At least one stop is required." }

  val entityMap: Map<StopId, GtfsStop> = entities.associateBy({ StopId.of(it.id) }, { it })

  val map = entities.associateBy({ StopId.of(it.id) }, {

    val locationType = determineLocationType(it, entityMap)
    val parentId = if (locationType.hasParent()) StopId.of(it.parentStation) else null
    val coordinates = Coordinates(it.lat, it.lon)

    with(it) {
      Stop(
        id = StopId.of(id),
        parentId = parentId,
        code = code,
        name = name,
        description = desc,
        coordinates = coordinates,
        zoneId = zoneId,
        url = url,
        locationType = locationType,
        timeZone = ZoneId.of(timeZone)
      )
    }
  })

  return Stops(map)
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

class Stops(val map: Map<StopId, Stop>)
