package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsLocationType;
import co.fastestpath.api.gtfs.GtfsStop;
import com.google.common.collect.BiMap;

/**
 * Determines if a stop is:
 *  - {@link LocationType.STATION} a station
 *  - {@link LocationType.STATION_STOP} a stop within a station
 *  - {@link LocationType.OUTSIDE_STOP} a stop not part of a station
 */
public class LocationTypeFactory {

  private LocationTypeFactory() {}

  public static LocationType fromStop(GtfsStop stop, BiMap<StopId, GtfsStop> stops) {
    switch (stop.getLocationType()) {
      case STATION:
        return LocationType.STATION;
      default:
        return isStationStop(stop, stops) ? LocationType.STATION_STOP : LocationType.OUTSIDE_STOP;
    }
  }

  /**
   * A stop belongs to a station only if its parent stop is marked as a station.
   */
  private static boolean isStationStop(GtfsStop stop, BiMap<StopId, GtfsStop> stops) {
    GtfsStop parent = stops.get(stop.getParentStation());
    return parent == null || parent.getLocationType() != GtfsLocationType.STATION;
  }
}
