package co.fastestpath.api.schedule

import co.fastestpath.api.bootstrap.archive.Archive
import co.fastestpath.api.bootstrap.archive.ArchiveEntity
import co.fastestpath.api.bootstrap.archive.parse.EntityMapper
import co.fastestpath.api.schedule.helpers.*
import co.fastestpath.gtfs.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Schedule Factory
 *
 * Schedule entities must be parsed in the following phases:
 * A. No dependencies - Agency, Calendar Dates (Services), Shape, Stop
 * B. Depend on A - Calendar, Route, Transfer
 * C. Depends on B - Fare Rule, Trip
 * D. Depends on C - Frequency, Stop Time
 */
@Singleton
class ScheduleFactory @Inject constructor(private val entityMapper: EntityMapper) {

  fun create(archive: Archive): Schedule {
    val entityMap = entityMapper.map(archive.files)

    val agencyEntities = entityMap[ArchiveEntity.AGENCY] as List<GtfsAgency>
    val agencies = createAgencies(agencyEntities)

    val calendarDateEntities = entityMap[ArchiveEntity.CALENDAR_DATES] as List<GtfsCalendarDate>
    val calendarDateMap = createCalendarDates(calendarDateEntities)

    val calendarEntities = entityMap[ArchiveEntity.CALENDAR] as List<GtfsCalendar>
    val calendars = createCalendars(calendarEntities, calendarDateMap)

    val shapeEntities = entityMap[ArchiveEntity.SHAPES] as List<GtfsShape>
    val shapes = createShapes(shapeEntities)

    val transferEntities = entityMap[ArchiveEntity.TRANSFERS] as List<GtfsTransfer>
    val transfers =

    val stopEntities = entityMap[ArchiveEntity.STOPS] as List<GtfsStop>
    val stops = createStops(stopEntities, transfers)

    val routeEntities = entityMap[ArchiveEntity.ROUTES] as List<GtfsRoute>
    val routes = createRoutes(routeEntities)
//
//    val stopTimeEntities = entityMap[ArchiveEntity.STOP_TIMES] as List<GtfsStopTime>
//    val stopTimes = createStopTimes(stopTimeEntities)

    // shapes
    // trips(shapes)

    return Schedule(
      calendars,
      trips,
      stops,
      fares
    )
  }
}
//
//
//
//    val stopMap = createStopMap(entityMap)
//    val stopTimeMap = createStopTimeMap(entityMap)
//    val tripMap = createTripMap(entityMap)
//
//    val calendars = (List<GtfsCalendar>) entityMap . get (GtfsEntityType.CALENDAR);
//    val calendarDates = (List<GtfsCalendarDate>) entityMap . get (GtfsEntityType.CALENDAR_DATES);
//
//    CalendarMap calendarMap = CalendarMapFactory . create (calendars, calendarDates, agencyMap.getFeedTimeZone());
//
//    return Schedule.clear()
//      .timeZone(agencyMap.getFeedTimeZone())
//      .agencies(agencyMap)
//      .trips(tripMap)
//      .routes(routeMap)
//      .stops(stopMap)
//      .stopTimes(stopTimeMap)
//      .calendar(calendarMap)
//      .build();
//  }
//
//  private fun createAgencies(agencyEntities: List<GtfsAgency>): Agencies {
//    Set<Agency> agencies = AgencyFactory.create(agencyEntities)
//    return AgencyMap.fromAgencies(agencies)
//  }
//
//  private fun StopMap createStopMap(GtfsEntityMap entityMap)
//  {
//    List<GtfsStop> stopEntities =(List<GtfsStop>) entityMap . get (GtfsEntityType.STOPS);
//    Set<Stop> stops = StopFactory . create (stopEntities);
//    return StopMap.fromStops(stops);
//  }
//
//  private fun RouteMap createRouteMap(GtfsEntityMap entityMap)
//  {
//    List<GtfsRoute> routeEntities =(List<GtfsRoute>) entityMap . get (GtfsEntityType.ROUTES);
//    Set<Route> routes = RouteFactory . create (routeEntities);
//    return RouteMap.fromRoutes(routes);
//  }
//
//  private fun StopTimeMap createStopTimeMap(GtfsEntityMap entityMap)
//  {
//    List<GtfsStopTime> stopTimeEntities =(List<GtfsStopTime>) entityMap . get (GtfsEntityType.STOP_TIMES);
//    Set<StopTime> stopTimes = StopTimeFactory . create (stopTimeEntities);
//    return StopTimeMap.create(stopTimes);
//  }
//
//  private fun TripMap createTripMap(GtfsEntityMap entityMap)
//  {
//    List<GtfsTrip> tripEntities =(List<GtfsTrip>) entityMap . get (GtfsEntityType.TRIPS);
//    Set<Trip> trips = TripFactory . create (tripEntities);
//    return TripMap.fromTrips(trips);
//  }
//}
//fun createSchedule(timeZone: ZoneId, entities: List<GtfsEntity>) {
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
