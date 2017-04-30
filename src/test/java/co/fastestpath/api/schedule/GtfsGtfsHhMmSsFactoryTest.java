package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.models.GtfsHhMmSs;
import co.fastestpath.api.gtfs.GtfsHhMmSsFactory;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GtfsGtfsHhMmSsFactoryTest {

  private static final Instant NOW = Instant.now();

  @Test
  public void testDefaultTimeZone() {
    ZonedDateTime zonedDateTime = NOW.atZone(GtfsHhMmSsFactory.DEFAULT_ZONE);
    int secondsSinceStartOfDay = zonedDateTime.getHour() * 3600 +
        zonedDateTime.getMinute() * 60 +
        zonedDateTime.getSecond();

    GtfsHhMmSs hhMmSs = GtfsHhMmSsFactory.create(NOW);
    assertEquals(hhMmSs.toSeconds(), secondsSinceStartOfDay);
  }

  @Test
  public void testDifferentTimeZones() {
    ZoneId nonDefaultZone = ZoneId.of("America/Los_Angeles");
    ZonedDateTime zonedDateTime = NOW.atZone(nonDefaultZone);
    int secondsSinceStartOfDay = zonedDateTime.getHour() * 3600 +
        zonedDateTime.getMinute() * 60 +
        zonedDateTime.getSecond();

    GtfsHhMmSs hhMmSs = GtfsHhMmSsFactory.create(NOW);
    assertNotEquals(hhMmSs.toSeconds(), secondsSinceStartOfDay);

    hhMmSs = GtfsHhMmSsFactory.create(NOW, nonDefaultZone);
    assertEquals(hhMmSs.toSeconds(), secondsSinceStartOfDay);
  }

}