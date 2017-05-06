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

    List<GtfsStop> stopEntities = (List<GtfsStop>) entityMap.get(GtfsEntityType.STOPS);
    Set<Stop> stops = StopFactory.create(stopEntities);

    StopMap stopMap = StopMap.fromStops(stops);

    List<StopTime> stopTimeEntities = (List<StopTime>) entityMap.get(GtfsEntityType.STOP_TIMES);
    Set<StopTime> stopTimes = StopTimeFactory.create(stopTimeEntities);

    List<GtfsCalendar> calendars = (List<GtfsCalendar>) entityMap.get(GtfsEntityType.CALENDAR);
    List<GtfsCalendarDate> calendarDates = (List<GtfsCalendarDate>) entityMap.get(GtfsEntityType.CALENDAR_DATES);
    Calendar calendar = CalendarFactory.create(calendars, calendarDates);
  }
}
