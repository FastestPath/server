package co.fastestpath.api.schedule;

import com.google.common.collect.*;

import java.util.Set;

public class StopMap {

  private final BiMap<StopId, Stop> stopIdMap;

  private StopMap(BiMap<StopId, Stop> stopIdMap) {
    this.stopIdMap = ImmutableBiMap.copyOf(stopIdMap);
  }

  public static StopMap fromStops(Set<Stop> stops) {
    BiMap<StopId, Stop> stopIdMap = createStopIdMap(stops);
    return new StopMap(stopIdMap);
  }

  private static BiMap<StopId, Stop> createStopIdMap(Set<Stop> stops) {
    BiMap<StopId, Stop> map = HashBiMap.create(stops.size());
    stops.forEach((stop) -> map.put(stop.getId(), stop));
    return map;
  }

  public Stop get(StopId stopId) {
    return stopIdMap.get(stopId);
  }
}
