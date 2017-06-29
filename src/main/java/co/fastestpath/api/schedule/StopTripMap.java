package co.fastestpath.api.schedule;

import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

public class StopTripMap {

  private final SetMultimap<StopId, TripId> stopTripMap;

  private StopTripMap(SetMultimap<StopId, TripId> tripsContainingStops) {
    this.stopTripMap = Multimaps.unmodifiableSetMultimap(tripsContainingStops);
  }

  // TODO: clean this up
  // continue from here
  public static StopTripMap create(StopMap stopMap, StopTimeMap stopTimeMap, Set<TripId> trips) {
    SetMultimap<StopId, TripId> stopTripMap = TreeMultimap.create();
    trips.forEach((tripId) -> {
      Set<StopTime> stopTimes = stopTimeMap.getStopTimes(tripId);
      stopTimes.forEach((stopTime) -> {

        stopTripMap.put(stopTime.getStopId(), tripId);

        Stop stop = stopMap.get(stopTime.getStopId());
        if (stop.getLocationType().hasParent()) {
          stopTripMap.put(stop.getParentId(), tripId);
        }

      });
    });
  }

  public Set<TripId> getTripsContainingStop(StopId stopId) {
    Set<TripId> tripsContainingStop = stopTripMap.get(stopId);
    return tripsContainingStop == null ? Collections.emptySet() : tripsContainingStop;
  }
}
