package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.mocks.MockCalendars;
import co.fastestpath.api.schedule.calendar.Calendar;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import co.fastestpath.api.schedule.calendar.CalendarExceptionDate;
import co.fastestpath.api.schedule.mocks.utils.DateUtils;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.ZoneId;

import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CalendarTest {

  private Calendar calendar = MockCalendars.CalendarA.INSTANCE;
  private ZoneId timeZone = calendar.getTimeZone();

  @Test
  public void testDaysOfWeek() {

    CalendarDate nextFriday = DateUtils.getNextDayOfWeek(DayOfWeek.FRIDAY, timeZone);
    boolean isNextFridayAvailable = calendar.isAvailable(nextFriday);
    assertTrue(isNextFridayAvailable);

    CalendarDate nextSunday = DateUtils.getNextDayOfWeek(DayOfWeek.SUNDAY, timeZone);
    boolean isNextSundayAvailable = calendar.isAvailable(nextSunday);
    assertTrue(isNextSundayAvailable);

    CalendarDate prevSunday = CalendarDate.builder()
        .dateString(DateFormatter.toString(nextSunday.getZonedDateTime().minusWeeks(1)))
        .timeZone(timeZone)
        .build();

    boolean isPrevSundayAvailable = calendar.isAvailable(prevSunday);
    assertFalse(isPrevSundayAvailable);
  }

  @Test
  public void testDateRange() {

    CalendarDate startDate = calendar.getStartDate();
    CalendarDate endDate = calendar.getEndDate();

    assertTrue(calendar.isAvailable(startDate));
    assertTrue(calendar.isAvailable(endDate));

    CalendarDate beforeStartDate = CalendarDate.builder()
        .dateString(DateFormatter.toString(startDate.getZonedDateTime().minusDays(1)))
        .timeZone(startDate.getTimeZone())
        .build();

    assertFalse(calendar.isAvailable(beforeStartDate));

    CalendarDate afterEndDate = CalendarDate.builder()
        .dateString(DateFormatter.toString(endDate.getZonedDateTime().plusDays(1)))
        .timeZone(startDate.getTimeZone())
        .build();

    assertFalse(calendar.isAvailable(afterEndDate));
  }

  @Test
  public void testExceptionDates() {
    // test added
    CalendarExceptionDate addedException = MockCalendars.CalendarA.EXCEPTION_DATE_ADDED;
    assertTrue(calendar.isAvailable(addedException.getDate()));

    // test removed
    CalendarExceptionDate removedException = MockCalendars.CalendarA.EXCEPTION_DATE_REMOVED;
    assertFalse(calendar.isAvailable(removedException.getDate()));
  }
}
