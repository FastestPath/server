package co.fastestpath.api.gtfs.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsCalendarDateExceptionType {
  ADDED(1),
  REMOVED(2);

  private final int code;

  @JsonCreator
  public static GtfsCalendarDateExceptionType fromCode(int code) {
    return code == ADDED.code ? ADDED : REMOVED;
  }

  GtfsCalendarDateExceptionType(int code) {
    this.code = code;
  }
}
