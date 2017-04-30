package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsEntityMappingException;
import co.fastestpath.api.gtfs.models.GtfsStop;
import co.fastestpath.api.gtfs.models.GtfsStopTime;
import co.fastestpath.api.schedule.models.*;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.time.Instant;
import java.util.List;

class ScheduleFactory {

  public static Schedule create(List<GtfsStop> stops, List<GtfsStopTime> stopTimes, Instant modifiedOn)
      throws GtfsEntityMappingException {

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
      departureMap.put(trip.getDeparture().getStation().getName(), trip);
    });
    return departureMap;
  }

  private ScheduleFactory() {}
}
