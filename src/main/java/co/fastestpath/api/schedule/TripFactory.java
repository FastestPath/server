package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.Station;
import co.fastestpath.api.gtfs.models.GtfsStopTime;
import co.fastestpath.api.schedule.models.Trip;

import java.util.*;
import java.util.stream.Collectors;

class TripFactory {

  public static List<Trip> createTrips(List<GtfsStopTime> stopTimes, List<Station> stations) {
    populateStopTimeStations(stopTimes, stations);
    List<Trip> trips = createTripsGroupedByTripId(stopTimes);
    return createSubTrips(trips);
  }

  private static List<Trip> createSubTrips(List<Trip> trips) {
    return trips.stream()
        .map(TripPartitioner::createSubTrips)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  private static void populateStopTimeStations(List<GtfsStopTime> stopTimes, List<Station> stations) {
    Map<String, Station> stationMap = createStationMap(stations);
    stopTimes.forEach((stopTime) -> {
      Station station = stationMap.get(stopTime.getStopId());
      stopTime.setStation(station);
    });
  }

  private static Map<String,Station> createStationMap(List<Station> stations) {
    Map<String, Station> stationMap = new HashMap<>();
    stations.forEach((station) -> {
      Set<String> stopIds = station.getStopIdMap().keySet();
      stopIds.forEach((stopId) -> stationMap.put(stopId, station));
    });
    return stationMap;
  }

  private static List<Trip> createTripsGroupedByTripId(List<GtfsStopTime> stopTimes) {
    Map<String, List<GtfsStopTime>> tripIdMap = stopTimes.stream()
        .collect(Collectors.groupingBy(GtfsStopTime::getTripId));

    Set<String> tripIds = tripIdMap.keySet();

    List<Trip> trips = new ArrayList<>(stopTimes.size());
    tripIds.forEach((tripId) -> {
      List<GtfsStopTime> tripTimes = tripIdMap.get(tripId);
      Trip trip = new Trip(tripTimes);
      trips.add(trip);
    });

    return trips;
  }

  private TripFactory() {
  }
}
