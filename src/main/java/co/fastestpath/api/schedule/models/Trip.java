package co.fastestpath.api.schedule.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class Trip implements Comparable<Trip> {

  private final LinkedList<StopTime> stopTimes;

  private final StopTime departure;

  private final StopTime arrival;

  public Trip(List<StopTime> stopTimes) {
    this.stopTimes = new LinkedList<>(stopTimes);
    this.departure = this.stopTimes.getFirst();
    this.arrival = this.stopTimes.getLast();
  }

  @JsonUnwrapped
  public Station getDepartureStation() {
    return departure.getStation();
  }

  @JsonUnwrapped
  public Station getArrivalStation() {
    return arrival.getStation();
  }

  public Instant getDepartureTime() {
    return departure.getDepartureTime();
  }

  public Instant getArrivalTime() {
    return arrival.getArrivalTime();
  }

  public LinkedList<StopTime> getStopTimes() {
    return stopTimes;
  }

  @Override
  public int compareTo(@Nonnull Trip other) {
    Instant departureTime = getDepartureTime();
    Instant otherDepartureTime = other.getDepartureTime();
    return departureTime.compareTo(otherDepartureTime);
  }
}
