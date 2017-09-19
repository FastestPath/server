package co.fastestpath.api.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

  private static final String PATTERN = "yyyyMMdd";

  private DateFormatter() {}

  public static Instant parse(String dateString, ZoneId timeZone) {
    SimpleDateFormat simpleDateFormat = createSimpleDateFormat(timeZone);
    try {
      return simpleDateFormat.parse(dateString).toInstant();
    } catch (ParseException e) {
      throw new IllegalArgumentException("Unable to parse date string: " + dateString);
    }
  }

  public static String toString(Instant instant, ZoneId timeZone) {
    SimpleDateFormat simpleDateFormat = createSimpleDateFormat(timeZone);
    return simpleDateFormat.format(Date.from(instant));
  }

  public static String toString(ZonedDateTime zonedDateTime) {
    return toString(zonedDateTime.toInstant(), zonedDateTime.getZone());
  }

  private static SimpleDateFormat createSimpleDateFormat(ZoneId timeZone) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);

    // IMPORTANT: must set timezone of formatted date
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

    return simpleDateFormat;
  }
}
