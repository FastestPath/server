package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.*;
import co.fastestpath.api.schedule.agency.Agency;
import co.fastestpath.api.schedule.agency.AgencyFactory;
import co.fastestpath.api.schedule.agency.AgencyMap;
import co.fastestpath.api.schedule.calendar.CalendarMap;
import co.fastestpath.api.schedule.calendar.CalendarMapFactory;
import co.fastestpath.api.schedule.route.Route;
import co.fastestpath.api.schedule.route.RouteFactory;
import co.fastestpath.api.schedule.route.RouteMap;
import co.fastestpath.api.schedule.trip.Trip;
import co.fastestpath.api.schedule.trip.TripFactory;
import co.fastestpath.api.schedule.trip.TripMap;

import java.util.List;
import java.util.Set;

public class ScheduleFactory {

  public static Schedule.ScheduleInstance create(GtfsEntityMap entityMap) {

    AgencyMap agencyMap = createAgencyMap(entityMap);

    RouteMap routeMap = createRouteMap(entityMap);

    StopMap stopMap = createStopMap(entityMap);

    StopTimeMap stopTimeMap = createStopTimeMap(entityMap);

    TripMap tripMap = createTripMap(entityMap);

    List<GtfsCalendar> calendars = (List<GtfsCalendar>) entityMap.get(GtfsEntityType.CALENDAR);
    List<GtfsCalendarDate> calendarDates = (List<GtfsCalendarDate>) entityMap.get(GtfsEntityType.CALENDAR_DATES);

    CalendarMap calendarMap = CalendarMapFactory.create(calendars, calendarDates, agencyMap.getFeedTimeZone());

    return Schedule.clear()
        .timeZone(agencyMap.getFeedTimeZone())
        .agencies(agencyMap)
        .trips(tripMap)
        .routes(routeMap)
        .stops(stopMap)
        .stopTimes(stopTimeMap)
        .calendar(calendarMap)
        .build();
  }

  private static AgencyMap createAgencyMap(GtfsEntityMap entityMap) {
    List<GtfsAgency> agencyEntities = (List<GtfsAgency>) entityMap.get(GtfsEntityType.AGENCY);
    Set<Agency> agencies = AgencyFactory.create(agencyEntities);
    return AgencyMap.fromAgencies(agencies);
  }

  private static StopMap createStopMap(GtfsEntityMap entityMap) {
    List<GtfsStop> stopEntities = (List<GtfsStop>) entityMap.get(GtfsEntityType.STOPS);
    Set<Stop> stops = StopFactory.create(stopEntities);
    return StopMap.fromStops(stops);
  }

  private static RouteMap createRouteMap(GtfsEntityMap entityMap) {
    List<GtfsRoute> routeEntities = (List<GtfsRoute>) entityMap.get(GtfsEntityType.ROUTES);
    Set<Route> routes = RouteFactory.create(routeEntities);
    return RouteMap.fromRoutes(routes);
  }

  private static StopTimeMap createStopTimeMap(GtfsEntityMap entityMap) {
    List<GtfsStopTime> stopTimeEntities = (List<GtfsStopTime>) entityMap.get(GtfsEntityType.STOP_TIMES);
    Set<StopTime> stopTimes = StopTimeFactory.create(stopTimeEntities);
    return StopTimeMap.create(stopTimes);
  }

  private static TripMap createTripMap(GtfsEntityMap entityMap) {
    List<GtfsTrip> tripEntities = (List<GtfsTrip>) entityMap.get(GtfsEntityType.TRIPS);
    Set<Trip> trips = TripFactory.create(tripEntities);
    return TripMap.fromTrips(trips);
  }
}
