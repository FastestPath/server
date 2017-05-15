package co.fastestpath.api.schedule;

import com.google.common.collect.*;

import java.util.Set;

public class StopMap {

  private final BiMap<StopId, Stop> map;

  public static StopMap fromStops(Set<Stop> stops) {
    BiMap<StopId, Stop> map = HashBiMap.create(stops.size());
    stops.forEach((stop) -> map.put(stop.getId(), stop));
    return new StopMap(map);
  }

  private StopMap(BiMap<StopId, Stop> map) {
    this.map = ImmutableBiMap.copyOf(map);
  }

  public Stop get(StopId stopId) {
    return map.get(stopId);
  }
}
