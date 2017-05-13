package co.fastestpath.api.schedule;

import com.google.common.collect.Multimaps;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

import java.util.Comparator;
import java.util.Set;

public class StopTimeMap {

  public static final Comparator<TripId> TRIP_ID_COMPARATOR = Comparator.comparing(TripId::toString);

  public static final Comparator<StopTime> ARRIVAL_COMPARATOR = Comparator.comparing(StopTime::getArrivalTime);

  public static final Comparator<StopTime> DEPARTURE_COMPARATOR = Comparator.comparing(StopTime::getDepartureTime);

  private final SortedSetMultimap<TripId, StopTime> arrivals;

  private final SortedSetMultimap<TripId, StopTime> departures;

  private StopTimeMap(Builder builder) {
    this.arrivals = Multimaps.unmodifiableSortedSetMultimap(builder.arrivals);
    this.departures = Multimaps.unmodifiableSortedSetMultimap(builder.departures);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Set<StopTime> getAllArrivalTimes(TripId tripId) {
    return arrivals.get(tripId);
  }

  public Set<StopTime> getAllDepartureTimes(TripId tripId) {
    return departures.get(tripId);
  }

  public static final class Builder {

    private SortedSetMultimap<TripId, StopTime> departures = TreeMultimap.create(TRIP_ID_COMPARATOR, ARRIVAL_COMPARATOR);

    private SortedSetMultimap<TripId, StopTime> arrivals = TreeMultimap.create(TRIP_ID_COMPARATOR, DEPARTURE_COMPARATOR);

    private Builder() {}

    public Builder put(TripId tripId, StopTime stopTime) {
      this.arrivals.put(tripId, stopTime);
      this.departures.put(tripId, stopTime);
      return this;
    }

    public StopTimeMap build() {
      return new StopTimeMap(this);
    }
  }
}
