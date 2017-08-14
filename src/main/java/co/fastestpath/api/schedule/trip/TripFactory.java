package co.fastestpath.api.schedule.trip;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.gtfs.GtfsTrip;
import co.fastestpath.api.schedule.ServiceId;
import co.fastestpath.api.schedule.ShapeId;
import co.fastestpath.api.schedule.route.RouteId;

import java.util.List;
import java.util.Set;

// TODO: test
public class TripFactory {

  private TripFactory() {}

  public static Set<Trip> create(List<GtfsTrip> trips) {
    return trips.stream()
        .map(TripFactory::createTrip)
        .collect(ImmutableCollectors.toSet());
  }

  private static Trip createTrip(GtfsTrip trip) {
    return Trip.builder()
        .id(new TripId(trip.getId()))
        .routeId(new RouteId(trip.getRouteId()))
        .serviceId(new ServiceId(trip.getServiceId()))
        .headsign(trip.getHeadSign())
        .shortName(trip.getShortName())
        .directionId(trip.getDirectionId())
        .blockId(trip.getBlockId())
        .shapeId(new ShapeId(trip.getShapeId()))
        .wheelchairAccessible(trip.getWheelchairAccessible())
        .bikesAllowed(trip.getBikesAllowed())
        .build();
  }
}
