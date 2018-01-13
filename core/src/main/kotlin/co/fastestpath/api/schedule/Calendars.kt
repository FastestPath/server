package co.fastestpath.api.schedule

import co.fastestpath.utils.SimpleDate
import com.google.common.collect.TreeMultimap
import java.time.DayOfWeek

class Calendars(
  calendars: List<Calendar>,
  calendarDates: List<CalendarDate>
) {

  private val exceptions: Map<SimpleDate, CalendarDate> = calendarDates.associateBy({ it.date }, { it })

  private val startDates: TreeMultimap<SimpleDate, Calendar> = TreeMultimap.create<SimpleDate, Calendar>()
  private val endDates: TreeMultimap<SimpleDate, Calendar> = TreeMultimap.create<SimpleDate, Calendar>()

  init {
    calendars.forEach {
      startDates.put(it.startDate, it)
      endDates.put(it.endDate, it)
    }
  }

  fun findServices(date: SimpleDate): Set<ServiceId> {

  }
}
