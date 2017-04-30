package co.fastestpath.api.schedule.models;

import co.fastestpath.api.gtfs.models.GtfsHhMmSs;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class GtfsHhMmSsTest {

  private static final int HOURS = 59;
  private static final int MINUTES = 59;
  private static final int SECONDS = 59;

  @Test
  public void testValidCreate() throws Exception {
    try {
      GtfsHhMmSs.create("00:00:00");
      GtfsHhMmSs.create("01:01:01");
      GtfsHhMmSs.create("59:59:59");
    } catch (IllegalArgumentException e) {
      fail("Failed to create valid HhMmSs.");
    }
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNull() {
    GtfsHhMmSs.create(null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testEmpty() {
    GtfsHhMmSs.create("");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInvalidFormat() {
    GtfsHhMmSs.create(":00:00");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInvalidRanges() {
    GtfsHhMmSs.create("99:99:99");
  }

  @Test
  public void testEquals() throws Exception {
    GtfsHhMmSs left = GtfsHhMmSs.create(HOURS + ":" + MINUTES + ":" + SECONDS);
    GtfsHhMmSs right = GtfsHhMmSs.builder()
        .hours(HOURS)
        .minutes(MINUTES)
        .seconds(SECONDS)
        .build();
    assertEquals(left, right);
  }

  @Test
  public void testCompareTo() throws Exception {
    GtfsHhMmSs left = GtfsHhMmSs.create(HOURS + ":" + MINUTES + ":" + SECONDS);
    GtfsHhMmSs right = GtfsHhMmSs.builder()
        .hours(HOURS)
        .minutes(MINUTES)
        .seconds(SECONDS)
        .build();

    assertEquals(left.compareTo(right), 0);

    GtfsHhMmSs zero = GtfsHhMmSs.create("00:00:00");
    assertEquals(zero.compareTo(left), -1);
    assertEquals(left.compareTo(zero), 1);
  }
}