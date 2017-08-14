package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import co.fastestpath.api.schedule.calendar.CalendarExceptionDate;
import co.fastestpath.api.schedule.calendar.CalendarExceptionMap;
import co.fastestpath.api.schedule.mocks.MockServices;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CalendarExceptionMapTest {

  private static final CalendarDate TODAY = CalendarDate.builder()
      .date(Instant.now())
      .timeZone(ZoneId.of("America/New_York"))
      .build();

  private static final CalendarDate TOMORROW = CalendarDate.builder()
      .date(Instant.now().plus(1, ChronoUnit.DAYS))
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