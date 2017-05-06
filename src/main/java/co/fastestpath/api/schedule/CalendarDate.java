package co.fastestpath.api.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

// TODO: test
public class CalendarDate {

  public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");

  private final Instant date;

  private CalendarDate(Instant date) {
    this.date = date;
  }

  public static CalendarDate fromString(String dateString) throws ParseException {
    return new CalendarDate(FORMAT.parse(dateString).toInstant());
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
