package co.fastestpath.api.schedule;

import co.fastestpath.gtfs.GtfsTimePointType;

public enum TimePoint {
  APPROXIMATE,
  EXACT;

  public static TimePoint from(GtfsTimePointType timePoint) {
    if (timePoint == null) {
      return null;
    } else if (timePoint == GtfsTimePointType.APPROXIMATE) {
      return APPROXIMATE;
    } else {
      return EXACT;
    }
  }
}
