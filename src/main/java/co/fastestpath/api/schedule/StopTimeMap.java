package co.fastestpath.api.schedule;

import com.google.common.collect.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

public class StopTimeMap {

  private static final Comparator<TripId> TRIP_ID_COMPARATOR = Comparator.comparing(TripId::toString);

  private static final Comparator<StopTime> SEQUENCE_COMPARATOR = Comparator.comparing(StopTime::getSequence);

  private final SortedSetMultimap<TripId, StopTime> trips;

  private final SetMultimap<StopId, TripId> tripsContainingStops;

  private StopTimeMap(TreeMultimap<TripId, StopTime> trips, SetMultimap<StopId, TripId> tripsContainingStops) {
    this.trips = Multimaps.unmodifiableSortedSetMultimap(trips);
    this.tripsContainingStops = Multimaps.unmodifiableSetMultimap(tripsContainingStops);
  }

  public static StopTimeMap create(Set<StopTime> stopTimes) {
    TreeMultimap<TripId, StopTime> trips = TreeMultimap.create(TRIP_ID_COMPARATOR, SEQUENCE_COMPARATOR);
    SetMultimap<StopId, TripId> tripsContainingStops = TreeMultimap.create();

    stopTimes.forEach((stopTime) -> {
      TripId tripId = stopTime.getTripId();
      trips.put(tripId, stopTime);
      tripsContainingStops.put(stopTime.getStopId(), tripId);

    });

    return new StopTimeMap(trips, tripsContainingStops);
  }

  public Set<TripId> getTripsContainingStop(StopId stopId) {
    Set<TripId> tripsContainingStop = tripsContainingStops.get(stopId);
    return tripsContainingStop == null ? Collections.emptySet() : tripsContainingStop;
  }

  // TODO: test that this is sorted
  public SortedSet<StopTime> getStopTimes(TripId tripId) {
    SortedSet<StopTime> stopTimes = trips.get(tripId);
    return stopTimes == null ? Collections.emptySet() : stopTimes;
  }
}
