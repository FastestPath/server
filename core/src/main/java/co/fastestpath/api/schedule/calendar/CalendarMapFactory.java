package co.fastestpath.api.schedule.calendar;

import co.fastestpath.gtfs.GtfsCalendar;
import co.fastestpath.gtfs.GtfsCalendarDate;
import co.fastestpath.api.schedule.ServiceId;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.time.ZoneId;
import java.util.*;

public class CalendarMapFactory {

  private CalendarMapFactory() {}

  public static CalendarMap create(List<GtfsCalendar> calendarEntities, List<GtfsCalendarDate> dateEntities, ZoneId timeZone) {

    Map<ServiceId, GtfsCalendar> calendarEntityMap = createCalendarEntityMap(calendarEntities);
    SetMultimap<ServiceId, GtfsCalendarDate> exceptionDateMap = createCalendarDateEntityMap(dateEntities);

    Set<co.fastestpath.api.schedule.calendar.Calendar> calendars = new HashSet<>(calendarEntityMap.size());
    calendarEntityMap.forEach((serviceId, calendarEntity) -> {
      co.fastestpath.api.schedule.calendar.Calendar calendar = createCalendar(serviceId, calendarEntity, exceptionDateMap, timeZone);
      calendars.add(calendar);
    });

    return CalendarMap.create(calendars);
  }

  private static co.fastestpath.api.schedule.calendar.Calendar createCalendar(ServiceId serviceId, GtfsCalendar calendarEntity,
      SetMultimap<ServiceId, GtfsCalendarDate> exceptionDateMap, ZoneId timeZone) {
    Set<GtfsCalendarDate> calendarExceptionDates = exceptionDateMap.get(serviceId);
    return CalendarFactory.createCalendar(serviceId, calendarEntity, calendarExceptionDates, timeZone);
  }

  private static Map<ServiceId, GtfsCalendar> createCalendarEntityMap(List<GtfsCalendar> calendars) {
    Map<ServiceId, GtfsCalendar> map = new HashMap<>(calendars.size());
    calendars.forEach((calendar) -> map.put(new ServiceId(calendar.getServiceId()), calendar));
    return map;
  }

  private static SetMultimap<ServiceId,GtfsCalendarDate> createCalendarDateEntityMap(List<GtfsCalendarDate> dates) {
    SetMultimap<ServiceId, GtfsCalendarDate> map = HashMultimap.create();
    dates.forEach((date) -> map.put(new ServiceId(date.getServiceId()), date));
    return map;
  }
}
