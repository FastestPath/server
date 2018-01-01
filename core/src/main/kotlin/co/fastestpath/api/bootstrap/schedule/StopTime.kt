package co.fastestpath.api.bootstrap.schedule

class StopTime(
  val tripId: TripId,
  val arrivalTime: HhMmSs,
  val departureTime: HhMmSs,
  val stopId: StopId,
  val sequence: Integer,
  val headSign: String,
  val pickupType: Integer,
  val dropOffType: Integer,
  val shapeDistanceTraveled: Float,
  val timePoint: TimePoint
)
