package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.*;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.time.Instant;
import java.util.List;

class ScheduleFactory {

  public static Schedule create(List<Stop> stops, List<StopTime> stopTimes, Instant modifiedOn)
      throws ScheduleParseException {

    List<Station> stations = StationGrouper.groupStopsByStation(stops);

    List<Trip> trips = TripFactory.createTrips(stopTimes, stations);

    Multimap<StationName, Trip> departureMap = createTripDepartureMap(trips);

    return Schedule.builder()
        .departureMap(departureMap)
        .modifiedOn(modifiedOn)
        .build();
  }

  /**
   * For each sequence, adds the following entries:
   * [ Hob ]  - Hob, 9th, 14th, 23rd, 33rd
   * [ 9th ]  - 9th, 14th, 23rd, 33rd
   * [ 14th ] - 14th, 23rd, 33rd
   * [ 23rd ] - 23rd, 33rd
   * Note: trip bucket entries are sorted by departure time.
   */
  private static Multimap<StationName, Trip> createTripDepartureMap(List<Trip> trips) {
    Multimap<StationName, Trip> departureMap = TreeMultimap.create();
    trips.forEach((trip) ->  departureMap.put(trip.getDepartureStation().getName(), trip));
    return departureMap;
  }

  private ScheduleFactory() {
  }
}
