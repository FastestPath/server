package co.fastestpath.api.schedule

class StopTime(
  val tripId: TripId,
  val arrivalTime: HhMmSs,
  val departureTime: HhMmSs,
  val stopId: StopId,
  val sequence: Int,
  val headSign: String,
  val pickupType: PickupDropoffType,
  val dropOffType: PickupDropoffType,
  val shapeDistanceTraveled: Float,
  val timePoint: TimePointType
)
