package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsFrequencyTimeType {
  APPROXIMATE,
  EXACT;

  @JsonCreator
  public static GtfsFrequencyTimeType from(Integer value) {
    return value == 1 ? EXACT : APPROXIMATE;
  }
}
