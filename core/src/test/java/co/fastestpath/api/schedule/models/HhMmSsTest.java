package co.fastestpath.api.schedule.models;

import co.fastestpath.api.schedule.HhMmSs;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class HhMmSsTest {

  private static final int HOURS = 59;
  private static final int MINUTES = 59;
  private static final int SECONDS = 59;

  @Test
  public void testValidCreate() throws Exception {
    try {
      HhMmSs.create("00:00:00");
      HhMmSs.create("01:01:01");
      HhMmSs.create("59:59:59");
    } catch (IllegalArgumentException e) {
      fail("Failed to create valid HhMmSs.");
    }
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNull() {
    HhMmSs.create(null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testEmpty() {
    HhMmSs.create("");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInvalidFormat() {
    HhMmSs.create(":00:00");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInvalidRanges() {
    HhMmSs.create("99:99:99");
  }

  @Test
  public void testEquals() throws Exception {
    HhMmSs left = HhMmSs.create(HOURS + ":" + MINUTES + ":" + SECONDS);
    HhMmSs right = HhMmSs.builder()
        .hours(HOURS)
        .minutes(MINUTES)
        .seconds(SECONDS)
        .build();
    assertEquals(left, right);
  }

  @Test
  public void testCompareTo() throws Exception {
    HhMmSs left = HhMmSs.create(HOURS + ":" + MINUTES + ":" + SECONDS);
    HhMmSs right = HhMmSs.builder()
        .hours(HOURS)
        .minutes(MINUTES)
        .seconds(SECONDS)
        .build();

    assertEquals(left.compareTo(right), 0);

    HhMmSs zero = HhMmSs.create("00:00:00");
    assertEquals(zero.compareTo(left), -1);
    assertEquals(left.compareTo(zero), 1);
  }
}