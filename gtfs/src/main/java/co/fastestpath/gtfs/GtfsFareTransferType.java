package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsFareTransferType {
  UNLIMITED_TRANSFERS(-1),
  NO_TRANSFERS(0),
  ONE_TRANSFER(1),
  TWO_TRANSFERS(2);

  private final int code;

  @JsonCreator
  public static final GtfsFareTransferType fromCode(Integer code) {
    if (code == 0) {
      return NO_TRANSFERS;
    }

    if (code == 1) {
      return ONE_TRANSFER;
    }

    if (code == 2) {
      return TWO_TRANSFERS;
    }

    return UNLIMITED_TRANSFERS;
  }

  GtfsFareTransferType(int code) {
    this.code = code;
  }
}
