package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.DepartAtArriveByType;
import co.fastestpath.api.schedule.ServiceId;
import co.fastestpath.api.schedule.StopId;
import co.fastestpath.api.schedule.trip.TripId;
import co.fastestpath.api.utils.ImmutableCollectors;
import co.fastestpath.api.scheduler.schedule.*;
import co.fastestpath.api.schedule.calendar.CalendarDate;
import co.fastestpath.api.schedule.calendar.CalendarMap;
import co.fastestpath.api.schedule.trip.TripMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class TripFinder {

  private static final Logger LOG = LoggerFactory.getLogger(TripFinder.class);

  private final Provider<CalendarMap> calendarMap;
  private final Provider<TripMap> tripMap;
  private final Provider<StopTraverser> stopTraverser;

  @Inject
  public TripFinder(Provider<CalendarMap> calendarMap, Provider<TripMap> tripMap,
      Provider<StopTraverser> stopTraverser) {
    this.calendarMap = calendarMap;
    this.tripMap = tripMap;
    this.stopTraverser = stopTraverser;
  }

  public TraversalResults find(StopId origin, StopId destination, DepartAtArriveByType type, CalendarDate date) {

    Set<ServiceId> availableServices = findAvailableServices(date);
    if (availableServices.isEmpty()) {
      LOG.info("Unable to services on date {}.", date);
      return TraversalResults.EMPTY;
    }

    Set<TripId> supportedTrips = availableServices.stream()
        .map(this::findTripsSupportingService)
        .flatMap(Set::stream)
        .collect(ImmutableCollectors.toSet());

    if (supportedTrips.isEmpty()) {
      LOG.info("No trips are supported by the available services.");
      return TraversalResults.EMPTY;
    }

    StopTraverser stopTraverser = this.stopTraverser.get();
    return stopTraverser.traverseTrips(origin, destination, supportedTrips);
  }

  /**
   * Find the services available for this date.
   */
  private Set<ServiceId> findAvailableServices(CalendarDate date) {
    CalendarMap calendarMap = this.calendarMap.get();
    return calendarMap.findAvailableServices(date);
  }

  /**
   * Get the trips associated with this service.
   */
  public Set<TripId> findTripsSupportingService(ServiceId serviceId) {
    TripMap tripMap = this.tripMap.get();
    return tripMap.getTripsforService(serviceId).stream()
        .collect(ImmutableCollectors.toSet());
  }
}
