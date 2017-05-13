package co.fastestpath.api.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// TODO: test
public class CalendarDate {

  public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");

  private final ZoneId timeZone;

  private final Instant date;

  private CalendarDate(Instant date, ZoneId timeZone) {
    this.timeZone = timeZone;
    this.date = date;
  }

  public static CalendarDate fromString(String dateString, ZoneId timeZone) throws ParseException {
    return new CalendarDate(FORMAT.parse(dateString).toInstant(), timeZone);
  }

  public boolean isBefore(CalendarDate other) {
    return date.isBefore(other.date);
  }

  public boolean isAfter(CalendarDate other) {
    return date.isAfter(other.date);
  }

  // TODO: test
  public DayOfWeek getDayOfTheWeek(ZoneId timeZone) {
    ZonedDateTime zonedDateTime = date.atZone(timeZone);
    return DayOfWeek.from(zonedDateTime);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CalendarDate that = (CalendarDate) o;

    return date != null ? date.equals(that.date) : that.date == null;
  }

  @Override
  public int hashCode() {
    return date != null ? date.hashCode() : 0;
  }
}
