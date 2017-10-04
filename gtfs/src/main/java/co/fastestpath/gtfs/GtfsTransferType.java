package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsTransferType {
  RECOMMENDED(0), // or null
  TIMED(1),
  MIN_TRANSFER_TIME(2),
  NOT_POSSIBLE(3);

  private final int code;

  @JsonCreator
  public static GtfsTransferType fromCode(Integer code) {

    if (code == 1) {
      return TIMED;
    }

    if (code == 2) {
      return MIN_TRANSFER_TIME;
    }

    if (code == 3) {
      return NOT_POSSIBLE;
    }

    return RECOMMENDED;
  }

  GtfsTransferType(int code) {
    this.code = code;
  }
}
