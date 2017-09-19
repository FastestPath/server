package co.fastestpath.api.schedule.trip;

import co.fastestpath.api.schedule.ServiceId;
import co.fastestpath.api.utils.ImmutableCollectors;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class TripMap {

  private final SetMultimap<ServiceId, Trip> map;

  private final Set<TripId> tripIds;

  private TripMap(Set<TripId> tripIds, SetMultimap<ServiceId, Trip> map) {
    this.map = ImmutableSetMultimap.copyOf(map);
    this.tripIds = ImmutableSet.copyOf(tripIds);
  }

  public static TripMap fromTrips(Set<Trip> trips) {
    SetMultimap<ServiceId, Trip> map = HashMultimap.create();
    trips.forEach((trip) -> map.put(trip.getServiceId(), trip));
    return new TripMap(mapTripIds(trips), map);
  }

  private static Set<TripId> mapTripIds(Collection<Trip> trips) {
    return trips.stream()
        .map(Trip::getId)
        .collect(ImmutableCollectors.toSet());
  }

  public Set<TripId> getTripsforService(ServiceId serviceId) {
    Set<Trip> trips = map.get(serviceId);
    return trips == null ? Collections.emptySet() : trips.stream()
        .map(Trip::getId)
        .collect(ImmutableCollectors.toSet());
  }

  public Set<TripId> getAllTrips() {
    return tripIds;
  }
}
