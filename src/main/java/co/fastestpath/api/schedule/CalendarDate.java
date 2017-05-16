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

  private final String holidayName;

  private final Instant date;

  private final ZoneId timeZone;

  private CalendarDate(Builder builder) {
    timeZone = builder.timeZone;
    holidayName = builder.holidayName;
    date = builder.date;
  }

  public CalendarDate(Instant date, ZoneId timeZone) {
    this.holidayName = null;
    this.date = date;
    this.timeZone = timeZone;
  }

  public static Builder builder() {
    return new Builder();
  }

  public ZoneId getTimeZone() {
    return timeZone;
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

  public static final class Builder {
    private ZoneId timeZone;
    private String holidayName;
    private Instant date;

    private Builder() {}

    public Builder timeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
      return this;
    }

    public Builder holidayName(String holidayName) {
      this.holidayName = holidayName;
      return this;
    }

    public Builder date(String date) throws ParseException {
      this.date = FORMAT.parse(date).toInstant();
      return this;
    }

    public Builder date(Instant date) {
      this.date = date;
      return this;
    }

    public CalendarDate build() {
      return new CalendarDate(this);
    }
  }
}
