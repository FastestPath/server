package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GtfsPaymentMethodType {
  ON_BOARD(0),
  BEFORE_BOARDING(1);

  private final int code;

  @JsonCreator
  public static GtfsPaymentMethodType fromCode(int code) {
    return code == ON_BOARD.code ? ON_BOARD : BEFORE_BOARDING;
  }

  GtfsPaymentMethodType(int code) {
    this.code = code;
  }
}
