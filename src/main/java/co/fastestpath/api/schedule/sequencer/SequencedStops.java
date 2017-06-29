package co.fastestpath.api.schedule.sequencer;

import co.fastestpath.api.schedule.StopTime;
import co.fastestpath.api.schedule.TripId;

import java.util.Collections;
import java.util.SortedSet;

public class SequencedStops {

  public static final SequencedStops EMPTY = new SequencedStops(null, Collections.emptySortedSet());

  private final TripId tripId;

  private final SortedSet<StopTime> sequence;

  private SequencedStops(TripId tripId, SortedSet<StopTime> sequence) {
    this.tripId = tripId;
    this.sequence = Collections.unmodifiableSortedSet(sequence);
  }

  public static SequencedStops create(TripId tripId, SortedSet<StopTime> sequence) {
    if (sequence == null || sequence.isEmpty()) {
      return EMPTY;
    }
    return new SequencedStops(tripId, sequence);
  }

  public boolean isEmpty() {
    return this == EMPTY;
  }

  public TripId getTripId() {
    return tripId;
  }

  public SortedSet<StopTime> getSequence() {
    return sequence;
  }
}
