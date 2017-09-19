package co.fastestpath.api.schedule.mocks;

import co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType;
import co.fastestpath.api.schedule.DateFormatter;
import co.fastestpath.api.schedule.calendar.Calendar;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import co.fastestpath.api.schedule.calendar.CalendarExceptionDate;
import co.fastestpath.api.schedule.mocks.utils.DateUtils;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static co.fastestpath.api.schedule.mocks.MockServices.SERVICE_A;
import static co.fastestpath.api.schedule.mocks.MockZoneId.NEW_YORK;

public class MockCalendars {

  public static class CalendarA {

    public static final ZoneId TIME_ZONE = NEW_YORK;

    public static final CalendarDate START_DATE = CalendarDate.builder()
        .dateString(DateFormatter.toString(ZonedDateTime.now(TIME_ZONE).minusYears(3)))
        .timeZone(TIME_ZONE)
        .build();

    public static final CalendarDate END_DATE = CalendarDate.builder()
        .dateString(DateFormatter.toString(ZonedDateTime.now(TIME_ZONE).plusMonths(3)))
        .timeZone(TIME_ZONE)
        .build();

    public static final CalendarDate REMOVED_DATE = CalendarDate.builder()
        .dateString(DateFormatter.toString(ZonedDateTime.now(TIME_ZONE).minusMonths(3)))
        .timeZone(TIME_ZONE)
        .build();

    public static final CalendarExceptionDate EXCEPTION_DATE_REMOVED = CalendarExceptionDate.builder()
        .date(REMOVED_DATE)
        .exceptionType(GtfsCalendarDateExceptionType.REMOVED)
        .serviceId(SERVICE_A)
        .build();

    public static final CalendarDate NEXT_SUNDAY = DateUtils.getNextDayOfWeek(DayOfWeek.SUNDAY, TIME_ZONE);
    public static final CalendarExceptionDate EXCEPTION_DATE_ADDED = CalendarExceptionDate.builder()
        .date(NEXT_SUNDAY)
        .exceptionType(GtfsCalendarDateExceptionType.ADDED)
        .serviceId(SERVICE_A)
        .build();

    public static final Calendar INSTANCE = createMockCalendarBuilder().build();
    public static final Calendar.Builder BUILDER = createMockCalendarBuilder();

    private static Calendar.Builder createMockCalendarBuilder() {
      return Calendar.builder()
          .serviceId(SERVICE_A)
          .timeZone(TIME_ZONE)
          .startDate(START_DATE)
          .endDate(END_DATE)
          .putExceptionDate(EXCEPTION_DATE_REMOVED)
          .putExceptionDate(EXCEPTION_DATE_ADDED)
          .putDayOfWeek(DayOfWeek.MONDAY, true)
          .putDayOfWeek(DayOfWeek.TUESDAY, true)
          .putDayOfWeek(DayOfWeek.WEDNESDAY, true)
          .putDayOfWeek(DayOfWeek.THURSDAY, true)
          .putDayOfWeek(DayOfWeek.FRIDAY, true)
          .putDayOfWeek(DayOfWeek.SATURDAY, false)
          .putDayOfWeek(DayOfWeek.SUNDAY, false);
    }
  }
}
