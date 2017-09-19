package co.fastestpath.api.schedule;

import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateFormatterTest {

  private static final ZoneId NEW_YORK = ZoneId.of("America/New_York");
  private static final ZoneId PARIS = ZoneId.of("Europe/Paris");

  @Test
  public void testConversion() {
    Instant now = Instant.now();

    String nowString = DateFormatter.toString(now, NEW_YORK);
    Instant instant = DateFormatter.parse(nowString, NEW_YORK);

    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, NEW_YORK);
    int year = zonedDateTime.getYear();
    int month = zonedDateTime.getMonthValue();
    int day = zonedDateTime.getDayOfMonth();
    String yyyyMMdd =  String.format("%04d%02d%02d", year, month, day);

    assertThat(yyyyMMdd, is(nowString));
  }

  @Test
  public void testTimezone() {
    String dateString = DateFormatter.toString(Instant.now(), NEW_YORK);
    Instant newYork = DateFormatter.parse(dateString, NEW_YORK);
    Instant paris = DateFormatter.parse(dateString, PARIS);
    assertThat(newYork, is(not(equalTo(paris))));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInvalidFormat() {
    DateFormatter.parse("invalid", NEW_YORK);
  }
}