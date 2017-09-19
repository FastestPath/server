package co.fastestpath.api.schedule.mocks.utils;

import co.fastestpath.api.schedule.DateFormatter;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import com.google.common.collect.ImmutableMap;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Map;
import java.util.TimeZone;

public class DateUtils {

  private static final Map<DayOfWeek, Integer> DAY_OF_WEEK_MAP = ImmutableMap.<DayOfWeek, Integer>builder()
      .put(DayOfWeek.MONDAY, java.util.Calendar.MONDAY)
      .put(DayOfWeek.TUESDAY, java.util.Calendar.TUESDAY)
      .put(DayOfWeek.WEDNESDAY, java.util.Calendar.WEDNESDAY)
      .put(DayOfWeek.THURSDAY, java.util.Calendar.THURSDAY)
      .put(DayOfWeek.FRIDAY, java.util.Calendar.FRIDAY)
      .put(DayOfWeek.SATURDAY, java.util.Calendar.SATURDAY)
      .put(DayOfWeek.SUNDAY, java.util.Calendar.SUNDAY)
      .build();

  private DateUtils() {}

  public static CalendarDate getNextDayOfWeek(DayOfWeek dayOfWeek, ZoneId timeZone) {
    java.util.Calendar calendar = java.util.Calendar.getInstance();
    calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
    calendar.set(java.util.Calendar.DAY_OF_WEEK, DAY_OF_WEEK_MAP.get(dayOfWeek));
    return CalendarDate.builder()
        .dateString(DateFormatter.toString(calendar.toInstant(), timeZone))
        .timeZone(timeZone)
        .build();
  }
}
