package co.fastestpath.api.schedule

import co.fastestpath.utils.SimpleDate
import java.time.DayOfWeek

class Calendar(
  val serviceId: ServiceId,
  val serviceName: String,
  val daysOfWeek: Set<DayOfWeek>,
  val startDate: SimpleDate,
  val endDate: SimpleDate,
  val exceptions: List<CalendarDate>
) : Comparable<Calendar> {
  override fun compareTo(other: Calendar): Int {
    return Comparator.comparing<Calendar, ServiceId>(Calendar::serviceId)
      .compare(this, other)
  }
}
