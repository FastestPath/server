package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.schedule.StopId;
import co.fastestpath.api.schedule.StopTime;
import co.fastestpath.api.schedule.StopTimeMap;
import co.fastestpath.api.schedule.trip.TripId;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Singleton
public class TripShifter {

  private final Provider<StopTimeMap> stopTimeMap;

  @Inject
  public TripShifter(Provider<StopTimeMap> stopTimeMap) {
    this.stopTimeMap = stopTimeMap;
  }

  public Set<ShiftedTrip> shiftToOrigin(StopId origin, Set<TripId> tripsContainingOrigin) {
    return tripsContainingOrigin.stream()
        .map((tripId) -> shiftToOrigin(origin, tripId))
        .filter((shiftedTrip) -> !shiftedTrip.isEmpty())
        .collect(ImmutableCollectors.toSet());
  }

  public ShiftedTrip shiftToOrigin(StopId origin, TripId tripContainingOrigin) {
    StopTimeMap stopTimeMap = this.stopTimeMap.get();
    SortedSet<StopTime> stopTimes = stopTimeMap.getStopTimes(tripContainingOrigin);

    SortedSet<StopTime> sequence = new TreeSet<>(stopTimes);

    while (sequence.size() > 1) {
      StopTime currentStopTime = sequence.first();
      if (currentStopTime.getStopId().equals(origin)) {
        return ShiftedTrip.create(tripContainingOrigin, sequence);
      }

      sequence.remove(currentStopTime);
    }

    // a sequence should have at least an origin and a destination
    return ShiftedTrip.EMPTY;
  }
}
