package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsCalendar;
import co.fastestpath.api.gtfs.GtfsCalendarDate;

import java.time.ZoneId;
import java.util.Set;

public class CalendarFactory {

  private CalendarFactory() {}

  public static Calendar createCalendar(ServiceId serviceId, GtfsCalendar calendarEntity,
      Set<GtfsCalendarDate> exceptionDates, ZoneId timeZone) {

    Calendar.Builder builder = Calendar.builder()
        .serviceId(serviceId)
        .startDate(new CalendarDate(calendarEntity.getStartDate(), timeZone))
        .endDate(new CalendarDate(calendarEntity.getEndDate(), timeZone));

    // add exception dates to calendar
    exceptionDates.stream()
        .map((calendarDateEntity) -> createCalendarExceptionDate(serviceId, calendarDateEntity, timeZone))
        .forEach((exceptionDate) -> builder.putExceptionDate(exceptionDate.getDate(), exceptionDate));

    // add supported days to the calendar
    calendarEntity.getDaysOfWeek()
        .forEach((dayOfWeek, isSupported) -> builder.putCalendarDate(dayOfWeek, isSupported));

    return builder.build();
  }

  private static CalendarExceptionDate createCalendarExceptionDate(ServiceId serviceId,
      GtfsCalendarDate calendarDateEntity, ZoneId timezone) {

    CalendarDate date = CalendarDate.builder()
        .holidayName(calendarDateEntity.getHolidayName())
        .date(calendarDateEntity.getDate())
        .timeZone(timezone)
        .build();

    return CalendarExceptionDate.builder()
        .serviceId(serviceId)
        .date(date)
        .exceptionType(calendarDateEntity.getExceptionType())
        .build();
  }
}
