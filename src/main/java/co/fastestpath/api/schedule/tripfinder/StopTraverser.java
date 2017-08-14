package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.*;
import co.fastestpath.api.schedule.trip.TripId;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class StopTraverser {

  private final Provider<StopTripMap> stopTripMap;
  private final Provider<StopMap> stopMap;

  private final TripShifter tripShifter;

  @Inject
  public StopTraverser(Provider<StopTripMap> stopTripMap, Provider<StopMap> stopMap, TripShifter tripShifter) {
    this.stopTripMap = stopTripMap;
    this.stopMap = stopMap;
    this.tripShifter = tripShifter;
  }

  public TraversalResults traverseTrips(StopId origin, StopId destination, Set<TripId> supportedTrips) {
    return new Traversal(origin, destination, supportedTrips).traverse();
  }

  private class Traversal {

    private final StopId destination;

    private final TraversalResults results;

    private final Set<TripId> supportedTrips;

    private final StopId initialOrigin;

    public Traversal(StopId origin, StopId destination, Set<TripId> supportedTrips) {
      this.initialOrigin = origin;
      this.supportedTrips = supportedTrips;
      this.destination = destination;
      this.results = new TraversalResults();
    }

    public TraversalResults traverse() {
      traverse(initialOrigin, Node.createRoot(initialOrigin));
      return results;
    }

    private void traverse(StopId origin, Node<StopId> parent) {
      Set<TripId> tripsContainingOrigin = stopTripMap.get().findTripsContainingStop(origin, supportedTrips);
      Set<ShiftedTrip> shiftedTrips = tripShifter.shiftToOrigin(origin, tripsContainingOrigin);
      shiftedTrips.forEach((trip) -> traverseBranch(trip, parent, new HashSet<>()));
    }

    private void traverseBranch(ShiftedTrip trip, Node<StopId> parent, Set<StopId> visited) {
      StopMap stopMap = StopTraverser.this.stopMap.get();

      if (trip.isEmpty()) {
        return;
      }

      StopTime stopTime = trip.next();
      StopId stopId = stopTime.getStopId();

      if (visited.contains(stopId)) {
        return;
      }

      Node<StopId> current = Node.create(parent, stopId);

      Stop stop = stopMap.get(stopId);
      if (stop.hasParent()) {
        traverse(stopId, current);
      }

      if (stopId.equals(destination)) {
        results.add(current);
        return;
      }
    }
  }
}
