package co.fastestpath.api.schedule;

import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GtfsHhMmSsFactoryTest {

  private static final Instant NOW = Instant.now();

  @Test
  public void testDefaultTimeZone() {
    ZonedDateTime zonedDateTime = NOW.atZone(GtfsHhMmSsFactory.DEFAULT_ZONE);
    int secondsSinceStartOfDay = zonedDateTime.getHour() * 3600 +
        zonedDateTime.getMinute() * 60 +
        zonedDateTime.getSecond();

    HhMmSs hhMmSs = GtfsHhMmSsFactory.create(NOW);
    assertEquals(hhMmSs.toSeconds(), secondsSinceStartOfDay);
  }

  @Test
  public void testDifferentTimeZones() {
    ZoneId nonDefaultZone = ZoneId.of("America/Los_Angeles");
    ZonedDateTime zonedDateTime = NOW.atZone(nonDefaultZone);
    int secondsSinceStartOfDay = zonedDateTime.getHour() * 3600 +
        zonedDateTime.getMinute() * 60 +
        zonedDateTime.getSecond();

    HhMmSs hhMmSs = GtfsHhMmSsFactory.create(NOW);
    assertNotEquals(hhMmSs.toSeconds(), secondsSinceStartOfDay);

    hhMmSs = GtfsHhMmSsFactory.create(NOW, nonDefaultZone);
    assertEquals(hhMmSs.toSeconds(), secondsSinceStartOfDay);
  }

}