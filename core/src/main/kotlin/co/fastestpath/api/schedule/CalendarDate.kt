package co.fastestpath.api.schedule

import co.fastestpath.utils.SimpleDate

class CalendarDate(
  val serviceId: ServiceId,
  val date: SimpleDate,
  val exceptionType: CalendarDateExceptionType
)
