package co.fastestpath.api.schedule.models;

import co.fastestpath.api.gtfs.models.GtfsStopTime;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class Trip implements Comparable<Trip> {

  private final LinkedList<GtfsStopTime> stopTimes;

  private final GtfsStopTime departure;

  private final GtfsStopTime arrival;

  private final Duration duration;

  public Trip(List<GtfsStopTime> stopTimes) {
    this.stopTimes = new LinkedList<>(stopTimes);
    this.departure = this.stopTimes.getFirst();
    this.arrival = this.stopTimes.getLast();
    this.duration = Duration.ofSeconds(arrival.getArrivalTime().toSeconds() - departure.getDepartureTime().toSeconds());
  }

  public GtfsStopTime getDeparture() {
    return departure;
  }

  public GtfsStopTime getArrival() {
    return arrival;
  }

  public List<GtfsStopTime> getStopTimes() {
    return stopTimes;
  }

  public Duration getDuration() {
    return duration;
  }

  @Override
  public int compareTo(Trip other) {
    // TODO: this should be moved to two different comparators
    // one for departures, one for arrivals
    return departure.getDepartureTime().compareTo(other.departure.getDepartureTime());
  }
}
