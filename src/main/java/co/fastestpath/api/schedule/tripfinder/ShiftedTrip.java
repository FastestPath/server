package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.StopTime;
import co.fastestpath.api.schedule.TripId;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

class ShiftedTrip {

  public static final ShiftedTrip EMPTY = new ShiftedTrip(null, Collections.emptySortedSet());

  private final TripId tripId;

  private final SortedSet<StopTime> sequence;

  private ShiftedTrip(TripId tripId, SortedSet<StopTime> sequence) {
    this.tripId = tripId;
    this.sequence = new TreeSet<>(sequence);
  }

  public static ShiftedTrip create(TripId tripId, SortedSet<StopTime> sequence) {
    if (sequence == null || sequence.isEmpty()) {
      return EMPTY;
    }
    return new ShiftedTrip(tripId, sequence);
  }

  public boolean isEmpty() {
    return this == EMPTY;
  }

  public TripId getTripId() {
    return tripId;
  }

  public boolean hasNext() {
    return !sequence.isEmpty();
  }

  public StopTime next() {
    if (sequence.isEmpty()) {
      return null;
    }

    StopTime first = sequence.first();
    sequence.remove(first);
    return first;
  }
}
