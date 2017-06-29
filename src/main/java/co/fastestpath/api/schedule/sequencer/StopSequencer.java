package co.fastestpath.api.schedule.sequencer;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.schedule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StopSequencer {

  private static final Logger LOG = LoggerFactory.getLogger(StopSequencer.class);

  private final StopTimeMap stopTimeMap;

  public StopSequencer(StopTimeMap stopTimeMap) {
    this.stopTimeMap = stopTimeMap;
  }

  public Set<SequencedStops> sequenceStopsForTrips(StopId origin, Set<TripId> tripsContainingOrigin) {
    return tripsContainingOrigin.stream()
        .map((tripId) -> sequenceStopsForTrip(origin, tripId))
        .filter((sequencedStops) -> !sequencedStops.isEmpty())
        .collect(ImmutableCollectors.toSet());
  }

  public SequencedStops sequenceStopsForTrip(StopId origin, TripId tripContainingOrigin) {
    SortedSet<StopTime> stopTimes = stopTimeMap.getStopTimes(tripContainingOrigin);

    // a sequence should have at least an origin and a destination
    if (stopTimes.size() < 1) {
      return SequencedStops.EMPTY;
    }

    // TODO: replace with takeWhile
    // TODO: extract to method, test
    SortedSet<StopTime> sequence = new TreeSet<>(stopTimes);
    while (sequence.size() > 1) {
      StopTime currentStopTime = sequence.first();
      // TODO: ensure equals implemented for StopIds
      if (currentStopTime.getStopId().equals(origin)) {
        return SequencedStops.create(tripContainingOrigin, sequence);
      }
      sequence.remove(currentStopTime);
    }

    return SequencedStops.EMPTY;
  }


  private static SortedSet<StopTime> sortStopTimes(Set<StopTime>) {

  }
}
