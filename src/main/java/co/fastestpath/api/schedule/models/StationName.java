package co.fastestpath.api.schedule.models;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum StationName {
  FOURTEENTH_STREET("14th Street"),
  TWENTY_THIRD_STREET("23rd Street"),
  THIRTY_THIRD_STREET("33rd Street"),
  NINTH_STREET("9th Street"),
  CHRISTOPHER_STREET("Christopher Street"),
  EXCHANGE_PLACE("Exchange Place"),
  GROVE_STREET("Grove Street"),
  HANGAR_SEVEN_SOUTH("Hangar 7 South"),
  HARRISON("Harrison"),
  HOBOKEN("Hoboken"),
  HOURLY_PARKING_CELL_PHONE_LOT_P5("Hourly Parking, Cell Phone Lot (P5)"),
  JOURNAL_SQUARE("Journal Square"),
  LONG_TERM_PARKING_P10("Long Term Parking (P10)"),
  LOT_P7("Lot P7"),
  MANAGERS_PARKING_LOT("Manager's Parking Lot"),
  NEWARK("Newark"),
  NEWPORT("Newport"),
  TERMINAL_A_P6("Terminal A, P6"),
  TERMINAL_A_P6_RENTAL_CAR_PICK_UP("Terminal A, P6, Rental Car Pick-Up"),
  TERMINAL_B_UPPER_LEVEL("Terminal B – Upper Level"),
  TERMINAL_C_LOWER_LEVEL_P4("Terminal C – Lower Level, P4"),
  TERMINAL_D_LOWER_LEVEL_STOP_ONE_P5("Terminal D - Lower Level - Stop 1, P5"),
  TERMINAL_D_LOWER_LEVEL_STOP_TWO_P4("Terminal D - Lower Level - Stop 2, P4"),
  WORLD_TRADE_CENTER("World Trade Center");

  private final String value;

  private static final Map<String, StationName> VALUE_MAP = Maps.uniqueIndex(
      Arrays.asList(StationName.values()), StationName::getValue);

  public static StationName fromValue(String value) {
    StationName stationName = VALUE_MAP.get(value);
    if (stationName == null) {
      throw new IllegalArgumentException("Unsupported StationName, value = " + value);
    }
    return stationName;
  }

  StationName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
