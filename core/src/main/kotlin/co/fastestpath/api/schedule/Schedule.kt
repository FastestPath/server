package co.fastestpath.api.schedule

import co.fastestpath.utils.isWithinDateRange
import java.time.Instant

class Schedule(
  val calendars: List<Calendar>,
  val agencies: Map<AgencyId, Agency>,
  val routes: Map<RouteId, Route>,
  val stops: Map<StopId, Stop>,
  val
//  val trips: Map<TripId, Trip>
) {
  fun getAvailableServices(date: Instant): List<ServiceId> {
    calendars.filter { isWithinDateRange(date, it.startDate, it.endDate) }

  }
}
//class Schedule private constructor(
//  val timeZone: ZoneId,
//  val agencies: Agencies,
//  val trips: TripMap,
//  val stopTimes: StopTimeMap,
//  val stopTrips: StopTripMap,
//  val routes: RouteMap,
//  val calendars: CalendarMap
//)
