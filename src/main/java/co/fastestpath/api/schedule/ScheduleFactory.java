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

  private static Multimap<StationName, Trip> createTripDepartureMap(List<Trip> trips) {
    Multimap<StationName, Trip> departureMap = TreeMultimap.create(Enum::compareTo, Trip::compareTo);
    trips.forEach((trip) ->  {
      departureMap.put(trip.getDepartureStation().getName(), trip);
    });
    return departureMap;
  }

  private ScheduleFactory() {
  }
}
