package co.fastestpath.api.schedule.calendar;

import org.testng.annotations.Test;

import java.time.ZoneId;

import static org.testng.AssertJUnit.assertTrue;

public class CalendarDateTest {

  private static final ZoneId TIME_ZONE = ZoneId.of("America/New_York");

  @Test
  public void testComparison() {
    CalendarDate one = CalendarDate.builder()
        .dateString("20000101")
        .timeZone(TIME_ZONE)
        .build();

    CalendarDate two = CalendarDate.builder()
        .dateString("20000102")
        .timeZone(TIME_ZONE)
        .build();

    assertTrue(one.compareTo(one) == 0);
    assertTrue(two.compareTo(one) > 0);
    assertTrue(two.compareTo(one) > 0);
  }
}