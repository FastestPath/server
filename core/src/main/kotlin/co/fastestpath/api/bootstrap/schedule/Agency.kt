package co.fastestpath.api.bootstrap.schedule

import java.time.ZoneId

class Agency(
  val id: AgencyId,
  val name: String,
  val url: String,
  val timeZone: ZoneId,
  val language: String,
  val phone: String,
  val fareUrl: String,
  val email: String
)
