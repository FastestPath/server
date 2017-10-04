package co.fastestpath.api.schedule.calendar;

import co.fastestpath.gtfs.GtfsCalendar;
import co.fastestpath.gtfs.GtfsCalendarDate;
import co.fastestpath.api.schedule.ServiceId;

import java.time.ZoneId;
import java.util.Set;

// TODO: test
public class CalendarFactory {

  private CalendarFactory() {}

  public static Calendar createCalendar(ServiceId serviceId, GtfsCalendar calendarEntity,
      Set<GtfsCalendarDate> exceptionDates, ZoneId timeZone) {

    CalendarDate startDate = CalendarDate.builder()
        .timeZone(timeZone)
        .dateString(calendarEntity.getStartDate())
        .build();

    CalendarDate endDate = CalendarDate.builder()
        .timeZone(timeZone)
        .dateString(calendarEntity.getEndDate())
        .build();

    Calendar.Builder builder = Calendar.builder()
        .serviceId(serviceId)
        .timeZone(timeZone)
        .startDate(startDate)
        .endDate(endDate);

    // add exception dates to calendar
    exceptionDates.stream()
        .map((calendarDateEntity) -> createCalendarExceptionDate(serviceId, calendarDateEntity, timeZone))
        .forEach(builder::putExceptionDate);

    // add supported days to the calendar
    calendarEntity.getDaysOfWeek()
        .forEach(builder::putDayOfWeek);

    return builder.build();
  }

  private static CalendarExceptionDate createCalendarExceptionDate(ServiceId serviceId,
      GtfsCalendarDate calendarDateEntity, ZoneId timezone) {

    CalendarDate date = CalendarDate.builder()
        .holidayName(calendarDateEntity.getHolidayName())
        .dateString(calendarDateEntity.getDate())
        .timeZone(timezone)
        .build();

    return CalendarExceptionDate.builder()
        .serviceId(serviceId)
        .date(date)
        .exceptionType(calendarDateEntity.getExceptionType())
        .build();
  }
}
