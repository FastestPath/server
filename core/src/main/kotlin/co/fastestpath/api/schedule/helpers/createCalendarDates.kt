package co.fastestpath.api.schedule.helpers

import co.fastestpath.api.schedule.CalendarDate
import co.fastestpath.api.schedule.CalendarDateExceptionType
import co.fastestpath.api.schedule.ServiceId
import co.fastestpath.gtfs.GtfsCalendarDate
import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.ListMultimap
import java.time.Instant

// TODO: test
fun createCalendarDates(calendarDateEntities: List<GtfsCalendarDate>): ListMultimap<ServiceId, CalendarDate> {
  val multiMap = ArrayListMultimap.create<ServiceId, CalendarDate>()
  calendarDateEntities.forEach {
    val serviceId = ServiceId.of(it.serviceId)
    val calendarDate = CalendarDate(
      serviceId = serviceId,
      date = Instant.parse(it.date),
      exceptionType = CalendarDateExceptionType.of(it.exceptionType)
    )
    multiMap.put(serviceId, calendarDate)
  }
  return multiMap
}
