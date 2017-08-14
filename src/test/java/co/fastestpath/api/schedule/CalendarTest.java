package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.calendar.Calendar;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import co.fastestpath.api.schedule.mocks.utils.DateUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static co.fastestpath.api.schedule.mocks.MockCalendars.CalendarA;
import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CalendarTest {


  @BeforeMethod
  public void setup() {
  }

  @Test
  public void testDaysOfWeek() {
    Calendar calendar = CalendarA.INSTANCE;
    ZoneId timeZone = calendar.getTimeZone();

    CalendarDate nextFriday = DateUtils.getNextDayOfWeek(DayOfWeek.FRIDAY, timeZone);
    boolean isNextFridayAvailable = calendar.isAvailable(nextFriday);
    assertTrue(isNextFridayAvailable);

    CalendarDate nextSunday = DateUtils.getNextDayOfWeek(DayOfWeek.SUNDAY, timeZone);
    boolean isNextSundayAvailable = calendar.isAvailable(nextSunday);
    assertTrue(isNextSundayAvailable);

    CalendarDate prevSunday = CalendarDate.builder()
        .date(ZonedDateTime.ofInstant(nextSunday.getDate(), timeZone).minusWeeks(1).toInstant())
        .timeZone(timeZone)
        .build();

    boolean isPrevSundayAvailable = calendar.isAvailable(prevSunday);
    assertFalse(isPrevSundayAvailable);
  }

  @Test
  public void testDateRange() {

  }

  @Test
  public void testEndDateInclusive() {

  }

  @Test
  public void testExceptionDates() {

  }
}
