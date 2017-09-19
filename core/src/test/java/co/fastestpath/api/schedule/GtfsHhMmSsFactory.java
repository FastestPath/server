package co.fastestpath.api.schedule;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class GtfsHhMmSsFactory {

  public static final ZoneId DEFAULT_ZONE = ZoneId.of("America/New_York");

  public static HhMmSs create(Instant instant) {
    return create(instant, DEFAULT_ZONE);
  }

  public static HhMmSs create(Instant instant, ZoneId timeZone) {
    ZonedDateTime desiredDate = instant.atZone(timeZone);
    return HhMmSs.builder()
        .hours(desiredDate.getHour())
        .minutes(desiredDate.getMinute())
        .seconds(desiredDate.getSecond())
        .build();
  }

  private GtfsHhMmSsFactory() {}
}
