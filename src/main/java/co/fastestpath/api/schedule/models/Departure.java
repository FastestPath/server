package co.fastestpath.api.schedule.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Departure {

  private static final Departure EMPTY = new Departure(null);

  @JsonUnwrapped
  private final Trip trip;

  public static Departure empty() {
    return EMPTY;
  }

  public Departure(Trip trip) {
    this.trip = trip;
  }
}
