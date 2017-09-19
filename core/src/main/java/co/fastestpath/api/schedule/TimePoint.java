package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsTimePoint;

public enum TimePoint {
  APPROXIMATE,
  EXACT;

  public static TimePoint from(GtfsTimePoint timePoint) {
    if (timePoint == null) {
      return null;
    } else if (timePoint == GtfsTimePoint.APPROXIMATE) {
      return APPROXIMATE;
    } else {
      return EXACT;
    }
  }
}
