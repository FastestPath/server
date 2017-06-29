package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.schedule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

public class TripFinder {

  private static final Logger LOG = LoggerFactory.getLogger(TripFinder.class);

  private final CalendarMap calendarMap;

  private final TripMap tripMap;

  private final StopMap stopMap;

  private final StopTimeMap stopTimeMap;

  private final OriginTripFinder originTripFinder;

  public TripFinder(Schedule schedule) {
    this.calendarMap = schedule.getCalendars();
    this.tripMap = schedule.getTrips();
    this.stopMap = schedule.getStops();
    this.stopTimeMap = schedule.getStopTimes();
    this.originTripFinder = new OriginTripFinder(schedule.getStopTrips(), tripMap);
  }

  public Optional<Trip> find(StopId origin, StopId destination, DepartAtArriveByType type, CalendarDate date) {

    Set<ServiceId> availableServices = findAvailableServices(date);
    if (availableServices.isEmpty()) {
      LOG.info("Unable to services on date {}.", date);
      return Optional.empty();
    }

    Set<TripId> tripsSupportingServices = availableServices.stream()
        .map(this::findTripsSupportingService)
        .flatMap(Set::stream)
        .collect(ImmutableCollectors.toSet());

    if (tripsSupportingServices.isEmpty()) {
      throw new TripFinderException("No trips supporting services were found");
    }

    Set<TripId> tripsContainingOrigin = originTripFinder.findTripsContainingOrigin(origin, tripsSupportingServices);



  }

  /**
   * Find the services available for this date.
   */
  private Set<ServiceId> findAvailableServices(CalendarDate date) {
    Set<Calendar> calendars = calendarMap.forDate(date);
    return calendars.stream()
        .map(Calendar::getServiceId)
        .collect(ImmutableCollectors.toSet());
  }

  /**
   * Get the trips associated with this service.
   */
  public Set<TripId> findTripsSupportingService(ServiceId serviceId) {
    return tripMap.getTrips(serviceId).stream()
        .map(Trip::getId)
        .collect(ImmutableCollectors.toSet());
  }



  // need an accumulator



    // next, determine which trips contain both the origin and destination

    // find all legs for this date
    // find leg that contains stop (initially origin)
    // move to origin stop on that leg
    // go to next stop
    // is Stop destination?
    //  - Yes -> done
    //  - No -> does Stop have a Transfer?
    //    - No -> done
    //    - Yes -> repeat from beginning
    // make sure time window of trip is still in range of service (start-end times)

    Set<Trip> trips = calendars.stream()
        .map(Calendar::getServiceId)
        .map(tripMap::getTrips)
        .flatMap(Set::stream)
        .filter((trip) -> doesTripContainStop(trip, origin))
        .filter((trip) -> doesTripContainStop(trip, destination))
        .filter((trip) -> originIsBeforeDestination(trip, origin, destination))
        .sort(sortByTripTime)
        .collect(ImmutableCollectors.toSet());
  }

  // TODO need to check transfers
  private boolean doesTripContainStop(Trip trip, StopId originId) {
    TripId tripId = trip.getId();
    return stopTimeMap.getAllArrivalTimes(tripId).stream()
        .map(StopTime::getStopId)
        .map(stopMap::get)
        .map(Stop::getId)
        .filter((stopId) -> stopId.equals(originId))
        .findAny()
        .isPresent();
  }

  private boolean isOriginBeforeDestination(Trip trip, StopId originId, StopId destinationId) {

  }
}
