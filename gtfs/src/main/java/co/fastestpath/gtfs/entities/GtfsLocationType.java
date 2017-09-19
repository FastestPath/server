package co.fastestpath.api.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsLocationType {
  STATION,
  STOP;

  @JsonCreator
  public static GtfsLocationType from(Integer value) {
    if (value == 1) {
      return GtfsLocationType.STATION;
    }
    return GtfsLocationType.STOP;
  }
}
