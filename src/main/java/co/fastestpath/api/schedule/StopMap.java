package co.fastestpath.api.schedule;

import com.google.common.collect.*;

import java.util.Set;

public class StopMap {

  private final BiMap<StopId, Stop> stopIdMap;

  private final Set<StopId> parentStopIds;

  private final SetMultimap<StopId, Stop> parentIdMap;

  public static StopMap fromStops(Set<Stop> stops) {
    BiMap<StopId, Stop> stopIdMap = createStopIdMap(stops);
    SetMultimap<StopId, Stop> parentIdMap = createParentIdMap(stops);
    return new StopMap(stopIdMap, parentIdMap);
  }

  private static SetMultimap<StopId, Stop> createParentIdMap(Set<Stop> stops) {
    SetMultimap<StopId, Stop> parentIdMap = HashMultimap.create();
    stops.forEach((stop) -> {
      LocationType locationType = stop.getLocationType();
      StopId id = locationType.hasParent() ? stop.getParentId() : stop.getId();
      parentIdMap.put(id, stop);
    });
    return parentIdMap;
  }

  private static BiMap<StopId, Stop> createStopIdMap(Set<Stop> stops) {
    BiMap<StopId, Stop> map = HashBiMap.create(stops.size());
    stops.forEach((stop) -> map.put(stop.getId(), stop));
    return map;
  }

  private StopMap(BiMap<StopId, Stop> stopIdMap, SetMultimap<StopId, Stop> parentIdMap) {
    this.stopIdMap = ImmutableBiMap.copyOf(stopIdMap);
    this.parentIdMap = ImmutableSetMultimap.copyOf(parentIdMap);
    this.parentStopIds = this.parentIdMap.keySet();
  }

  public Stop get(StopId stopId) {
    return stopIdMap.get(stopId);
  }

  public Set<Stop> getAllStops(StopId stopId) {
    return parentIdMap.get(stopId);
  }

  public Set<StopId> getParentStopIds() {
    return parentStopIds;
  }

  public SetMultimap<StopId, Stop> getParentIdMap() {
    return parentIdMap;
  }
}
