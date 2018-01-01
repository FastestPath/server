package co.fastestpath.api.bootstrap.schedule

class Schedule(
  val agencies: Map<AgencyId, Agency>,
  val routes: Map<RouteId, Route>,
  val stops: Map<StopId, Stop>
)
//class Schedule private constructor(
//  val timeZone: ZoneId,
//  val agencies: Agencies,
//  val trips: TripMap,
//  val stopTimes: StopTimeMap,
//  val stopTrips: StopTripMap,
//  val routes: RouteMap,
//  val calendars: CalendarMap
//)
