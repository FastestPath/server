package co.fastestpath.api.schedule.models;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Departure {

  private static final Departure EMPTY = new Departure();

  private StationName departureStation;

  private Instant departureTime;

  private StationName arrivalStation;

  private Instant arrivalTime;

  private List<Stop> stops;

  public static Departure empty() {
    return EMPTY;
  }

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
