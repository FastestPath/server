package co.fastestpath.api.schedule.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Departure {

  private static final Departure EMPTY = new Departure();

  @JsonUnwrapped
  private final Sequence sequence;

  public static Departure empty() {
    return EMPTY;
  }

  private Departure() {
    this.sequence = new Sequence();
  }

  public Departure(Sequence sequence) {
    this.sequence = sequence;
  }
}
