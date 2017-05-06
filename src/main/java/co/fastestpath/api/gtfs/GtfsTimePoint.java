package co.fastestpath.api.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsTimePoint {
  APPROXIMATE,
  EXACT;

  @JsonCreator
  public static GtfsTimePoint from(Integer value) {
    return value == 0 ? APPROXIMATE : EXACT;
  }
}
