package co.fastestpath.api.schedule.calendar;

import co.fastestpath.api.schedule.DateFormatter;

import javax.annotation.Nonnull;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CalendarDate implements Comparable<CalendarDate> {

  private final String dateString;

  private final Instant date;

  private final ZoneId timeZone;

  private final String holidayName;

  private CalendarDate(Builder builder) {
    dateString = builder.dateString;
    timeZone = builder.timeZone;
    holidayName = builder.holidayName;
    date = DateFormatter.parse(dateString, timeZone);
  }

  public static Builder builder() {
    return new Builder();
  }

  public ZoneId getTimeZone() {
    return timeZone;
  }

  public ZonedDateTime getZonedDateTime() {
    return ZonedDateTime.ofInstant(date, timeZone);
  }

  public DayOfWeek getDayOfTheWeekAtZone(ZoneId timeZone) {
    ZonedDateTime zonedDateTime = date.atZone(timeZone);
    return DayOfWeek.from(zonedDateTime);
  }

  @Override
  public int compareTo(@Nonnull CalendarDate other) {
    return date.compareTo(other.date);
  }

  @Override
  public String toString() {
    return dateString;
  }

  public static final class Builder {
    private String dateString;
    private ZoneId timeZone;
    private String holidayName;

    private Builder() {}

    public Builder dateString(String dateString) {
      this.dateString = dateString;
      return this;
    }

    public Builder timeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
      return this;
    }

    public Builder holidayName(String holidayName) {
      this.holidayName = holidayName;
      return this;
    }

    public CalendarDate build() {
      return new CalendarDate(this);
    }
  }
}
