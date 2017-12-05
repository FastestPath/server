package co.fastestpath.api.schedule;

import co.fastestpath.api.bootstrap.schedule.Stop;
import co.fastestpath.api.bootstrap.schedule.StopId;
import co.fastestpath.api.schedule.trip.TripId;
import co.fastestpath.api.schedule.trip.TripMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Set;

@Singleton
public class StopTripMap {

  private final Provider<StopTripMap> stopMap;
  private final Provider<StopTimeMap> stopTimeMap;
  private final Provider<TripMap> tripMap;

  private SetMultimap<StopId, TripId> map;

  @Inject
  public StopTripMap(Provider<StopTripMap> stopMap, Provider<StopTimeMap> stopTimeMap, Provider<TripMap> tripMap) {
    this.stopMap = stopMap;
    this.stopTimeMap = stopTimeMap;
    this.tripMap = tripMap;
    this.map = null;
  }

  public Set<TripId> findTripsContainingStop(StopId stopId) {
    populateMap();
    Set<TripId> tripsContainingStop = map.get(stopId);
    return tripsContainingStop == null ? Collections.emptySet() : tripsContainingStop;
  }

  public Set<TripId> findTripsContainingStop(StopId trips, Set<TripId> candidateTrips) {
    Set<TripId> tripsContainingStop = findTripsContainingStop(trips);
    tripsContainingStop.retainAll(candidateTrips);
    return tripsContainingStop;
  }

  private synchronized void populateMap() {
    if (this.map != null) {
      return;
    }

    SetMultimap<StopId, TripId> map = HashMultimap.create();

    TripMap tripMap = this.tripMap.get();
    Set<TripId> trips = tripMap.getAllTrips();
    trips.forEach(this::addTripStops);

    this.map = Multimaps.unmodifiableSetMultimap(map);
  }

  private void addTripStops(TripId tripId) {
    StopTimeMap stopTimeMap = this.stopTimeMap.get();
    Set<StopTime> stopTimes = stopTimeMap.getStopTimes(tripId);
    stopTimes.forEach((stopTime) -> {

      StopTripMap stopMap = this.stopMap.get();
      Stop stop = stopMap.get(stopTime.getStopId());

      if (stop.hasParent()) {
        map.put(stop.getParentId(), tripId);
      }

      map.put(stopTime.getStopId(), tripId);

    });
  }
}
