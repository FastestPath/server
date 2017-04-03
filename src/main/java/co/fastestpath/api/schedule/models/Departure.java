package co.fastestpath.api.schedule.models;

import com.google.common.annotations.VisibleForTesting;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Departure {

  private StationName departureStation;

  private Instant departureTime;

  private StationName arrivalStation;

  private Instant arrivalTime;

  private List<Stop> stops;

  private Departure() {
  }

  public static Departure create(Trip trip) {
    Departure departure = new Departure();
    departure.departureStation = trip.getDepartureStation().getName();
    departure.departureTime = trip.getDepartureTime();
    departure.arrivalStation = trip.getArrivalStation().getName();
    departure.arrivalTime = trip.getArrivalTime();
    departure.stops = trip.getStopTimes().stream()
        .map(Stop::create)
        .collect(Collectors.toList());
    return departure;
  }

  public StationName getDepartureStation() {
    return departureStation;
  }

  public Instant getDepartureTime() {
    return departureTime;
  }

  public StationName getArrivalStation() {
    return arrivalStation;
  }

  public Instant getArrivalTime() {
    return arrivalTime;
  }

  public List<Stop> getStops() {
    return stops;
  }

  private static class Stop {

    private StationName station;

    private Instant arrivalTime;

    private Instant departureTime;

    private String stopSequence;

    static Stop create(StopTime stopTime) {
      Stop stop = new Stop();
      stop.station = stopTime.getStation().getName();
      stop.arrivalTime = stopTime.getArrivalTime();
      stop.departureTime = stopTime.getDepartureTime();
      stop.stopSequence = stopTime.getStopSequence();
      return stop;
    }

    private Stop() {
    }
  }
}
