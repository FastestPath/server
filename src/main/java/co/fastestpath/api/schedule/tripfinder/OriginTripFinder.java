package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.StopId;
import co.fastestpath.api.schedule.StopTripMap;
import co.fastestpath.api.schedule.TripId;
import co.fastestpath.api.schedule.TripMap;

import java.util.Set;

class OriginTripFinder {

  private final StopTripMap stopTripMap;

  private final TripMap tripMap;

  public OriginTripFinder(StopTripMap stopTripMap, TripMap tripMap) {
    this.stopTripMap = stopTripMap;
    this.tripMap = tripMap;
  }

  public Set<TripId> findTripsContainingOrigin(StopId origin, Set<TripId> availableTrips) {
    Set<TripId> tripsContainingStop = stopTripMap.getTripsContainingStop(origin);
    tripsContainingStop.retainAll(availableTrips);
    return tripsContainingStop;
  }
}
