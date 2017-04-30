package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.models.GtfsStop;
import co.fastestpath.api.schedule.models.Station;
import co.fastestpath.api.schedule.models.StationName;
import com.google.common.collect.ArrayListMultimap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class StationGrouper {

  public static List<Station> groupStopsByStation(List<GtfsStop> stops) {
    ArrayListMultimap<StationName, GtfsStop> stationMap = ArrayListMultimap.create();

    stops.forEach((stop) -> {
      StationName stationName = StationName.fromValue(stop.getName());
      stationMap.put(stationName, stop);
    });

    Set<StationName> stationNames = stationMap.keySet();
    return stationNames.stream()
        .map((stationName) -> createStation(stationName, stationMap.get(stationName)))
        .collect(Collectors.toList());
  }

  private static Station createStation(StationName stationName, List<GtfsStop> stationsStops) {
    Map<String, GtfsStop> stopIdMap = new HashMap<>(stationsStops.size());

    // group stops by id
    stationsStops.forEach((stop) -> stopIdMap.put(stop.getId(), stop));

    return new Station.Builder()
        .name(stationName)
        .stopIdMap(stopIdMap)
        .build();
  }
}
