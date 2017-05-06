package co.fastestpath.api.gtfs;

public enum GtfsLocationType {
  STATION,
  STOP;

  public static GtfsLocationType from(Integer value) {
    if (value == 1) {
      return GtfsLocationType.STATION;
    }
    return GtfsLocationType.STOP;
  }
}
