package co.fastestpath.api.schedule;

import co.fastestpath.api.utils.ImmutableCollectors;
import co.fastestpath.api.gtfs.GtfsStop;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.List;
import java.util.Set;

public class StopFactory {

  private StopFactory() {}

  public static Set<Stop> create(List<GtfsStop> stops) {

    BiMap<StopId, GtfsStop> stopIdMap = createStopIdMap(stops);

    return stops.stream()
        .map((stop) -> createStop(stop, stopIdMap))
        .collect(ImmutableCollectors.toSet());
  }

  private static Stop createStop(GtfsStop stop, BiMap<StopId, GtfsStop> stopIdMap) {

    LocationType locationType = LocationTypeFactory.fromStop(stop, stopIdMap);

    StopId parentId = locationType.hasParent() ? new StopId(stop.getParentStation()) : null;

    Coordinates coordinates = Coordinates.builder()
        .latitude(stop.getLat())
        .longitude(stop.getLon())
        .build();

    return Stop.builder()
        .id(new StopId(stop.getId()))
        .parentId(parentId)
        .code(stop.getCode())
        .name(stop.getName())
        .description(stop.getDesc())
        .coordinates(coordinates)
        .zoneId(stop.getZoneId())
        .url(stop.getUrl())
        .locationType(locationType)
        .build();
  }

  private static BiMap<StopId,GtfsStop> createStopIdMap(List<GtfsStop> stops) {
    BiMap<StopId, GtfsStop> map = HashBiMap.create(stops.size());
    stops.forEach((stop) -> map.put(new StopId(stop.getId()), stop));
    return ImmutableBiMap.copyOf(map);
  }
}
