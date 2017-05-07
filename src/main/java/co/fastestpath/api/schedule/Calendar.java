package co.fastestpath.api.schedule;

import com.google.common.collect.ImmutableMap;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * First checks for `calendar_dates` then falls back to `calendar`.
 * Links to {@link Trip} via {@link ServiceId}
 */
public class Calendar {

  private final ServiceId serviceId;

  // TODO: filter using start and end dates

  private final CalendarDate startDate;

  private final CalendarDate endDate;

  private final Map<CalendarDate, CalendarExceptionDate> exceptionDateMap;

  private final Map<DayOfWeek, CalendarDate> calendarDateMap;

  private Calendar(Builder builder) {
    serviceId = builder.serviceId;
    startDate = builder.startDate;
    endDate = builder.endDate;
    exceptionDateMap = ImmutableMap.copyOf(builder.exceptionDateMap);
    calendarDateMap = ImmutableMap.copyOf(builder.calendarDateMap);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Optional<CalendarExceptionDate> getExceptionDate(CalendarDate date) {
    return isDateInRange(date)
        ? Optional.ofNullable(exceptionDateMap.get(date))
        : Optional.empty();
  }


  public Optional<CalendarDate> getCalendarDate(CalendarDate date) {
    return calendarDateMap.get(dayOfWeek);
  }

  private boolean isDateInRange(CalendarDate date) {
    return !(date.isBefore(startDate) || date.isAfter(endDate));
  }

  public static final class Builder {

    private ServiceId serviceId;

    private CalendarDate startDate;
    private CalendarDate endDate;

    private Map<CalendarDate, CalendarExceptionDate> exceptionDateMap = new HashMap<>();

    private Map<DayOfWeek, CalendarDate> calendarDateMap = new HashMap<>();

    private Builder() {}

    public Builder serviceId(ServiceId serviceId) {
      this.serviceId = serviceId;
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

    public Builder putExceptionDate(CalendarDate date, CalendarExceptionDate exceptionDate) {
      this.exceptionDateMap.put(date, exceptionDate);
      return this;
    }

    public Builder putCalendarDate(DayOfWeek dayOfWeek, CalendarDate calendarDate) {
      this.calendarDateMap.put(dayOfWeek, calendarDate);
      return this;
    }

    public Calendar build() {
      return new Calendar(this);
    }
  }
}
