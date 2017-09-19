package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import co.fastestpath.api.schedule.calendar.CalendarExceptionDate;
import co.fastestpath.api.schedule.mocks.MockServices;
import co.fastestpath.api.schedule.calendar.CalendarExceptionMap;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CalendarExceptionMapTest {

  private static final ZoneId TIME_ZONE = ZoneId.of("America/New_York");

  private static final CalendarDate TODAY = CalendarDate.builder()
      .dateString(DateFormatter.toString(Instant.now(), TIME_ZONE))
      .timeZone(TIME_ZONE)
      .build();

  private static final CalendarDate TOMORROW = CalendarDate.builder()
      .dateString(DateFormatter.toString(ZonedDateTime.now(TIME_ZONE).plusDays(1)))
      .timeZone(ZoneId.of("America/New_York"))
      .build();

  private static final CalendarExceptionDate EXCEPTION_DATE = CalendarExceptionDate.builder()
      .date(TODAY)
      .serviceId(MockServices.SERVICE_A)
      .exceptionType(GtfsCalendarDateExceptionType.REMOVED)
      .build();

  private final CalendarExceptionMap map = CalendarExceptionMap.builder()
      .put(EXCEPTION_DATE)
      .build();

  @Test
  public void testExceptionMap() {
    assertTrue(map.get(TODAY).isPresent());
    assertFalse(map.get(TOMORROW).isPresent());
  }
}