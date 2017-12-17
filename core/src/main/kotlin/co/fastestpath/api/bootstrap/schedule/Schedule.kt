package co.fastestpath.api.bootstrap.schedule

import co.fastestpath.gtfs.GtfsEntity
import java.time.ZoneId

fun createSchedule(timeZone: ZoneId, entities: List<GtfsEntity>) {
//  return Schedule(
//    timeZone = timeZone,
//    agencies = createAgencies(entities.get(AGENCY) as List<GtfsAgency>),
//    trips = createTrips(entities.get(TRIPS) as List<GtfsTrip>),
//    stops = createStops(entities.get(STOPS) as List<GtfsStop>),
//    stopTimes = createStopTimes(entities.get(STOP_TIMES) as List<GtfsStopTime>),
//    stopTrips = createStopTrips(),
//    routes = createRoutes(entities.get(ROUTES) as List<GtfsRoute>),
//    calendars = createCalendars(entities.get(CALENDAR) as List<GtfsCalendar>)
//  )
}
//
class Schedule {}
//class Schedule private constructor(
//  val timeZone: ZoneId,
//  val agencies: Agencies,
//  val trips: TripMap,
//  val stopTimes: StopTimeMap,
//  val stopTrips: StopTripMap,
//  val routes: RouteMap,
//  val calendars: CalendarMap
//)
