package co.fastestpath.api.schedule;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;

import java.util.Collections;
import java.util.Set;

public class TripMap {

  private final SetMultimap<ServiceId, Trip> map;

  private TripMap(SetMultimap<ServiceId, Trip> map) {
    this.map = ImmutableSetMultimap.copyOf(map);
  }

  public static TripMap fromTrips(Set<Trip> trips) {
    SetMultimap<ServiceId, Trip> map = HashMultimap.create();
    trips.forEach((trip) -> map.put(trip.getServiceId(), trip));
    return new TripMap(map);
  }

  public Set<Trip> getTrips(ServiceId serviceId) {
    Set<Trip> trips = map.get(serviceId);
    return trips == null ? Collections.emptySet() : trips;
  }
}
