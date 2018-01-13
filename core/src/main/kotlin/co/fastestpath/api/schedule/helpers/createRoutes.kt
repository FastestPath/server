package co.fastestpath.api.schedule.helpers

import co.fastestpath.api.schedule.AgencyId
import co.fastestpath.api.schedule.Route
import co.fastestpath.api.schedule.RouteId
import co.fastestpath.gtfs.GtfsRoute

fun createRoutes(routes: List<GtfsRoute>): Map<RouteId, Route> {
  return routes.associateBy(
    { RouteId.of(it.id) }, {
    Route(
      id = RouteId.of(it.id),
      agencyId = AgencyId.of(it.agencyId),
      shortName = it.shortName,
      longName = it.longName,
      description = it.description,
      type = it.description,
      url = it.url,
      color = it.color,
      textColor = it.textColor
    )
  })
}