package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsTimePointType {
  APPROXIMATE,
  EXACT;

  @JsonCreator
  public static GtfsTimePointType from(Integer value) {
    return value == 0 ? APPROXIMATE : EXACT;
  }
}
