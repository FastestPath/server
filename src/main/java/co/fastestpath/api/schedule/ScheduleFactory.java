package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.*;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public class ScheduleFactory {

  private ScheduleFactory() {}

  public static Schedule create(GtfsEntityMap entityMap) {

    List<GtfsAgency> agencyEntities = (List<GtfsAgency>) entityMap.get(GtfsEntityType.AGENCY);
    Set<Agency> agencies = AgencyFactory.create(agencyEntities);

    AgencyMap agencyMap = AgencyMap.fromAgencies(agencies);

    List<GtfsRoute> routeEntities = (List<GtfsRoute>) entityMap.get(GtfsEntityType.ROUTES);
    Set<Route> routes = RouteFactory.create(routeEntities);

    RouteMap routeMap = RouteMap.fromRoutes(routes);

    List<GtfsStop> stopEntities = (List<GtfsStop>) entityMap.get(GtfsEntityType.STOPS);
    Set<Stop> stops = StopFactory.create(stopEntities);

    StopMap stopMap = StopMap.fromStops(stops);

    List<GtfsStopTime> stopTimeEntities = (List<GtfsStopTime>) entityMap.get(GtfsEntityType.STOP_TIMES);
    Set<StopTime> stopTimes = StopTimeFactory.create(stopTimeEntities);

    StopTimeMap stopTimeMap = StopTimeMap.create(stopTimes);

    List<GtfsTrip> tripEntities = (List<GtfsTrip>) entityMap.get(GtfsEntityType.TRIPS);
    Set<Trip> trips = TripFactory.create(tripEntities);

    List<GtfsCalendar> calendars = (List<GtfsCalendar>) entityMap.get(GtfsEntityType.CALENDAR);
    List<GtfsCalendarDate> calendarDates = (List<GtfsCalendarDate>) entityMap.get(GtfsEntityType.CALENDAR_DATES);
    Calendar calendar = CalendarFactory.create(calendars, calendarDates);
  }
}
