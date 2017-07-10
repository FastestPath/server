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

  private final TripShifter tripShifter;

  private final StopTraverser stopTraverser;

  public TripFinder(Schedule schedule) {
    this.calendarMap = schedule.getCalendars();
    this.tripMap = schedule.getTrips();
    this.stopMap = schedule.getStops();
    this.stopTimeMap = schedule.getStopTimes();
    this.originTripFinder = new OriginTripFinder(schedule.getStopTrips(), tripMap);
    this.tripShifter = new TripShifter(stopTimeMap);
    this.stopTraverser = new StopTraverser()
  }

  public Optional<Trip> find(StopId origin, StopId destination, DepartAtArriveByType type, CalendarDate date) {

    Set<ServiceId> availableServices = findAvailableServices(date);
    if (availableServices.isEmpty()) {
      LOG.info("Unable to services on date {}.", date);
      return Optional.empty();
    }

    Set<TripId> supportedTrips = availableServices.stream()
        .map(this::findTripsSupportingService)
        .flatMap(Set::stream)
        .collect(ImmutableCollectors.toSet());

    if (supportedTrips.isEmpty()) {
      throw new TripFinderException("No trips found for the available services.");
    }

    Set<TraversalResults> traversalResults = stopTraverser.traverseSequences(origin, destination, supportedTrips);

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
}
