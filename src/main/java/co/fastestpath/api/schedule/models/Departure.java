package co.fastestpath.api.schedule.models;

import co.fastestpath.api.gtfs.models.GtfsHhMmSs;
import co.fastestpath.api.gtfs.models.GtfsStopTime;

import java.util.List;
import java.util.stream.Collectors;

public class Departure {

  private StationName departureStation;

  private GtfsHhMmSs departureTime;

  private StationName arrivalStation;

  private GtfsHhMmSs arrivalTime;

  private List<Stop> stops;

  private Departure() {
  }

  public static Departure create(Trip trip) {
    Departure departure = new Departure();
    departure.departureStation = trip.getDeparture().getStation().getName();
    departure.departureTime = trip.getDeparture().getDepartureTime();
    departure.arrivalStation = trip.getArrival().getStation().getName();
    departure.arrivalTime = trip.getArrival().getArrivalTime();
    departure.stops = trip.getStopTimes().stream()
        .map(Stop::create)
        .collect(Collectors.toList());
    return departure;
  }

  public StationName getDepartureStation() {
    return departureStation;
  }

  public GtfsHhMmSs getDepartureTime() {
    return departureTime;
  }

  public StationName getArrivalStation() {
    return arrivalStation;
  }

  public GtfsHhMmSs getArrivalTime() {
    return arrivalTime;
  }

  public List<Stop> getStops() {
    return stops;
  }

  private static class Stop {

    private StationName station;

    private GtfsHhMmSs arrivalTime;

    private GtfsHhMmSs departureTime;

    private String stopSequence;

    static Stop create(GtfsStopTime stopTime) {
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
