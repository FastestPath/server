package co.fastestpath.api.schedule.calendar;

import co.fastestpath.api.schedule.ServiceId;
import co.fastestpath.api.schedule.trip.Trip;
import co.fastestpath.gtfs.GtfsCalendarDateExceptionType;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Optional;

/**
 * First checks for `calendar_dates` then falls back to `calendar`.
 * Links to {@link Trip} via {@link ServiceId}
 */
public class Calendar {

  private final ServiceId serviceId;

  private final ZoneId timeZone;

  private final CalendarDate startDate;

  private final CalendarDate endDate; // inclusive

  private final DayOfWeekMap dayOfWeekMap;

  private final CalendarExceptionMap exceptionMap;

  private Calendar(Builder builder) {
    serviceId = builder.serviceId;
    timeZone = builder.timeZone;
    startDate = builder.startDate;
    endDate = builder.endDate;
    dayOfWeekMap = builder.dayOfWeekMap.build();
    exceptionMap = builder.exceptionMap.build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public ZoneId getTimeZone() {
    return timeZone;
  }

  public Optional<CalendarExceptionDate> getExceptionDate(CalendarDate date) {
    return exceptionMap.get(date);
  }

  public boolean isAvailable(CalendarDate calendarDate) {
    if (!isDateInRange(calendarDate)) {
      return false;
    }

    Optional<CalendarExceptionDate> exceptionDateOptional = getExceptionDate(calendarDate);
    if (exceptionDateOptional.isPresent()) {
      return exceptionDateOptional.get().getExceptionType() == GtfsCalendarDateExceptionType.ADDED;
    }

    DayOfWeek dayOfTheWeekAtZone = calendarDate.getDayOfTheWeekAtZone(timeZone);
    return dayOfWeekMap.isDaySupported(dayOfTheWeekAtZone);
  }

  // TODO: test equals
  private boolean isDateInRange(CalendarDate date) {
    return startDate.compareTo(date) <= 0 && date.compareTo(endDate) <= 0;
  }

  public CalendarDate getStartDate() {
    return startDate;
  }

  public CalendarDate getEndDate() {
    return endDate;
  }

  public static final class Builder {

    private ServiceId serviceId;
    private ZoneId timeZone;

    private CalendarDate startDate;
    private CalendarDate endDate;

    private DayOfWeekMap.Builder dayOfWeekMap = DayOfWeekMap.builder();
    private CalendarExceptionMap.Builder exceptionMap = CalendarExceptionMap.builder();

    private Builder() {}

    public Builder serviceId(ServiceId serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder timeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
      return this;
    }

    public Builder startDate(CalendarDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(CalendarDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public Builder putDayOfWeek(DayOfWeek dayOfWeek, Boolean isSupported) {
      this.dayOfWeekMap.put(dayOfWeek, isSupported);
      return this;
    }

    public Builder putExceptionDate(CalendarExceptionDate exceptionDate) {
      this.exceptionMap.put(exceptionDate);
      return this;
    }

    public Calendar build() {
      return new Calendar(this);
    }
  }
}
