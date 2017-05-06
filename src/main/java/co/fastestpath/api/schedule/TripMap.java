package co.fastestpath.api.schedule;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.util.Set;

public class TripMap {

  private final SetMultimap<ServiceId, Trip> map;

  private TripMap(Builder builder) {
    map = builder.map;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Set<Trip> getAll(ServiceId serviceId) {
    return map.get(serviceId);
  }

  public static final class Builder {

    private SetMultimap<ServiceId, Trip> map = HashMultimap.create();

    private Builder() {}

    public Builder put(ServiceId serviceId, Trip trip) {
      this.map.put(serviceId, trip);
      return this;
    }

    public TripMap build() {
      return new TripMap(this);
    }
  }
}
