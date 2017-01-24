package co.fastestpath.api.schedule.models;

import com.google.common.collect.Multimap;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Schedule {

  private final Multimap<String, Sequence> departureMap;

  private final Instant modifiedOn;

  public Schedule(Multimap<String, Sequence> departureMap, Instant modifiedOn) {
    this.departureMap = departureMap;
    this.modifiedOn = modifiedOn;
  }

  public Optional<Sequence> getSequence(StationName from, StationName to, Instant departAt) {
    Collection<Sequence> departures = departureMap.get(from.getValue());
    Optional<Sequence> sequenceOptional = departures.stream()
        .filter((departure) -> isAfterDesiredDepartureTime(departure, departAt))
        .filter((departure) -> isDestinationPresent(departure, to))
        .sorted(Schedule::compareDepartureTimes)
        .findFirst();

    if (!sequenceOptional.isPresent()) {
      return Optional.empty();
    }

    Sequence sequence = sequenceOptional.get();
    LinkedList<Arrival> allArrivals = sequence.getArrivals();
    int destinationIndex = sequence.getArrivals().stream()
        .map((arrival) -> arrival.getStation().getName())
        .collect(Collectors.toList())
        .indexOf(to.getValue());

    List<Arrival> arrivals = allArrivals.subList(0, destinationIndex + 1);
    return Optional.of(new Sequence(arrivals));
  }

  public Instant getModifiedOn() {
    return modifiedOn;
  }

  /**
   * The provided schedules don't include dates, only hours, minutes, seconds.
   */
  private static boolean isAfterDesiredDepartureTime(Sequence departure, Instant desiredDeparture) {
    Date departureDate = Date.from(departure.getArrivals().get(0).getDepartureTime());
    departureDate.setDate(1);

    Date desiredDepartureDate = Date.from(desiredDeparture);
    desiredDepartureDate.setDate(1);

    return departureDate.toInstant().isAfter(desiredDepartureDate.toInstant());
  }

  private static boolean isDestinationPresent(Sequence origin, StationName destination) {
    return origin.getArrivals().stream()
        .map((arrival) -> arrival.getStation().getName())
        .anyMatch((stationName) -> stationName.equals(destination.getValue()));
  }

  private static int compareDepartureTimes(Sequence left, Sequence right) {
    Instant leftDeparture = left.getArrivals().get(0).getDepartureTime();
    Instant rightDeparture = right.getArrivals().get(0).getDepartureTime();
    return leftDeparture.compareTo(rightDeparture);
  }
}

