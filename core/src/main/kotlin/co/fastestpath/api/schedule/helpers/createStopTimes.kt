package co.fastestpath.api.schedule.helpers

import co.fastestpath.api.schedule.*
import co.fastestpath.gtfs.GtfsStopTime

fun createStopTimes(entities: List<GtfsStopTime>): List<StopTime> {
  return entities.map {
    StopTime(
      tripId = TripId.of(it.tripId),
      arrivalTime = HhMmSs(it.arrivalTime),
      departureTime = HhMmSs(it.departureTime),
      stopId = StopId.of(it.stopId),
      sequence = it.stopSequence,
      headSign = it.stopHeadSign,
      pickupType = PickupDropoffType.of(it.pickupType),
      dropOffType = PickupDropoffType.of(it.dropOffType),
      shapeDistanceTraveled = it.shapeDistTraveled,
      timePoint = TimePointType.of(it.timePoint)
    )
  }
}
