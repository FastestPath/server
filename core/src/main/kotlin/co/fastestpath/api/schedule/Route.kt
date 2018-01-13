package co.fastestpath.api.schedule

class Route(
  val id: RouteId,
  val agencyId: AgencyId,
  val shortName: String,
  val longName: String,
  val description: String,
  val type: String,
  val url: String,
  val color: String,
  val textColor: String
)
