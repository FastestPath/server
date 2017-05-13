package co.fastestpath.api.schedule;

import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

import java.util.Comparator;
import java.util.Set;

public class StopTimeMapFactory {

  private StopTimeMapFactory() {}

  public static StopTimeMapFactory create()

    private SortedSetMultimap<TripId, StopTime> departures = TreeMultimap.create(TRIP_ID_COMPARATOR, ARRIVAL_COMPARATOR);

    private SortedSetMultimap<TripId, StopTime> arrivals = TreeMultimap.create(TRIP_ID_COMPARATOR, DEPARTURE_COMPARATOR);

    private Builder() {}

    public Builder put(TripId tripId, StopTime stopTime) {
      this.arrivals.put(tripId, stopTime);
      this.departures.put(tripId, stopTime);
      return this;
    }

    public StopTimeMapFactory build() {
      return new StopTimeMapFactory(this);
    }
  }
}
