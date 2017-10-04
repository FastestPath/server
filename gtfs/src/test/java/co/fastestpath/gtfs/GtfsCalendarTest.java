package co.fastestpath.gtfs;

import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GtfsCalendarTest {

  @Test
  public void testDaysOfWeekEmpty() {
    GtfsCalendar calendar = GtfsCalendar.builder()
        .build();

    assertTrue(calendar.getDaysOfWeek()
        .isEmpty());
  }

  @Test
  public void testDaysOfWeekValues() {
    GtfsCalendar calendar = GtfsCalendar.builder()
        .monday(1)
        .tuesday(0)
        .build();

    Set<DayOfWeek> daysOfWeek = calendar.getDaysOfWeek();
    assertTrue(daysOfWeek.contains(DayOfWeek.MONDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.TUESDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.WEDNESDAY));
  }

  @Test
  public void testAllDays() {
    GtfsCalendar calendar = GtfsCalendar.builder()
        .monday(1)
        .tuesday(1)
        .wednesday(1)
        .thursday(1)
        .friday(1)
        .saturday(1)
        .sunday(1)
        .build();

    Set<DayOfWeek> daysOfWeek = calendar.getDaysOfWeek();
    assertTrue(daysOfWeek.size() == 7);
    assertTrue(daysOfWeek.contains(DayOfWeek.MONDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.TUESDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.WEDNESDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.THURSDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.FRIDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.SATURDAY));
    assertFalse(daysOfWeek.contains(DayOfWeek.SUNDAY));
  }
}