package co.fastestpath.api.schedule.helpers

import co.fastestpath.api.schedule.Calendar
import co.fastestpath.api.schedule.CalendarDate
import co.fastestpath.api.schedule.Calendars
import co.fastestpath.api.schedule.ServiceId
import co.fastestpath.gtfs.GtfsCalendar
import com.google.common.collect.ListMultimap
import java.time.Instant

fun createCalendars(
  calendarEntities: List<GtfsCalendar>,
  calendarDateMap: ListMultimap<ServiceId, CalendarDate>
): Calendars {
  return calendarEntities.map {

    val serviceId = ServiceId.of(it.serviceId)
    val exceptions = calendarDateMap[serviceId]

    Calendar(
      serviceId = serviceId,
      serviceName = it.serviceName,
      daysOfWeek = it.daysOfWeek,
      startDate = Instant.parse(it.startDate),
      endDate = Instant.parse(it.endDate),
      exceptions = exceptions
    )
  }
}
