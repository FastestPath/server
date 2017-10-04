package co.fastestpath.api.bootstrap.gtfs.archive

import co.fastestpath.gtfs.*

class ArchiveEntity private constructor(val filename: String, val clazz: Class<out GtfsEntity>) {

  companion object {
    val AGENCY = ArchiveEntity("agency.txt", GtfsAgency::class.java)
    val CALENDAR = ArchiveEntity("calendar.txt", GtfsCalendar::class.java)
    val CALENDAR_DATES = ArchiveEntity("calendar_dates.txt", GtfsCalendarDate::class.java)
    val FARE_ATTRIBUTES = ArchiveEntity("fare_attributes.txt", GtfsFareAttributes::class.java)
    val FARE_RULES = ArchiveEntity("fare_rules.txt", GtfsFareRules::class.java)
    val FEED_INFO = ArchiveEntity("feed_info.txt", GtfsFeedInfo::class.java)
    val FREQUENCIES = ArchiveEntity("frequencies.txt", GtfsFrequency::class.java)
    val ROUTES = ArchiveEntity("routes.txt", GtfsRoute::class.java)
    val SHAPES = ArchiveEntity("shapes.txt", GtfsShape::class.java)
    val STOPS = ArchiveEntity("stops.txt", GtfsStop::class.java)
    val STOP_TIMES = ArchiveEntity("stop_times.txt", GtfsStopTime::class.java)
    val TRANSFERS = ArchiveEntity("transfers.txt", GtfsTransfer::class.java)
    val TRIPS = ArchiveEntity("trips.txt", GtfsTrip::class.java)

    fun getAll(): List<ArchiveEntity> {
      return listOf(
        AGENCY,
        CALENDAR,
        CALENDAR_DATES,
        FARE_ATTRIBUTES,
        FARE_RULES,
        FEED_INFO,
        FREQUENCIES,
        ROUTES,
        SHAPES,
        STOPS,
        STOP_TIMES,
        TRANSFERS,
        TRIPS
      )
    }
  }
}

