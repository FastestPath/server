package co.fastestpath.api.schedule.route;

import co.fastestpath.api.schedule.agency.AgencyId;
import co.fastestpath.api.utils.ImmutableCollectors;
import co.fastestpath.api.gtfs.GtfsRoute;

import java.util.List;
import java.util.Set;

public class RouteFactory {

  private RouteFactory() {}

  public static final Set<Route> create(List<GtfsRoute> routes) {
    return routes.stream()
        .map(RouteFactory::createRoute)
        .collect(ImmutableCollectors.toSet());
  }

  private static Route createRoute(GtfsRoute route) {
    return Route.builder()
        .id(new RouteId(route.getId()))
        .agencyId(new AgencyId(route.getAgencyId()))
        .shortName(route.getShortName())
        .description(route.getDescription())
        .type(route.getType())
        .url(route.getUrl())
        .color(route.getColor())
        .textColor(route.getTextColor())
        .build();
  }
}
