package co.fastestpath.api.schedule;

public enum LocationType {
  STATION,
  STATION_STOP,
  OUTSIDE_STOP;

  public boolean hasParent() {
    return this == STATION_STOP;
  }
}
