package co.fastestpath.api.schedule;

import com.google.common.collect.ImmutableMap;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * First checks for `calendar_dates` then falls back to `calendar`.
 * Links to {@link Trip} via {@link ServiceId}
 */
public class Calendar {

  private final ServiceId serviceId;

  private final String serviceName;

  private final ZoneId timeZone;

  private final CalendarDate startDate;

  private final CalendarDate endDate;

  private final Map<CalendarDate, CalendarExceptionDate> exceptionDateMap;

  private final Map<DayOfWeek, Boolean> calendarDateMap;

  private Calendar(Builder builder) {
    serviceId = builder.serviceId;
    serviceName = builder.serviceName;
    timeZone = builder.timeZone;
    startDate = builder.startDate;
    endDate = builder.endDate;
    exceptionDateMap = ImmutableMap.copyOf(builder.exceptionDateMap);
    calendarDateMap = ImmutableMap.copyOf(builder.calendarDateMap);
  }

  public static Builder builder() {
    return new Builder();
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public ZoneId getTimeZone() {
    return timeZone;
  }

  public Optional<CalendarExceptionDate> getExceptionDate(CalendarDate date) {
    return isDateInRange(date)
        ? Optional.ofNullable(exceptionDateMap.get(date))
        : Optional.empty();
  }

  public boolean isServiceAvailable(CalendarDate calendarDate) {
    if (!isDateInRange(calendarDate)) {
      return false;
    }

    if (exceptionDateMap.get(calendarDate) != null) {
      return false;
    }

    DayOfWeek dayOfTheWeekAtZone = calendarDate.getDayOfTheWeekAtZone(timeZone);
    return calendarDateMap.get(dayOfTheWeekAtZone) != null;
  }

  // TODO: test
  private boolean isDateInRange(CalendarDate date) {
    return !(date.isBefore(startDate) || date.isAfter(endDate));
  }

  public static final class Builder {

    private ServiceId serviceId;
    private ZoneId timeZone;
    private String serviceName;

    private CalendarDate startDate;
    private CalendarDate endDate;

    private Map<CalendarDate, CalendarExceptionDate> exceptionDateMap = new HashMap<>();

    private Map<DayOfWeek, Boolean> calendarDateMap = new HashMap<>();

    private Builder() {}

    public Builder serviceId(ServiceId serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder timeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
      return this;
    }

    public Builder serviceName(String serviceName) {
      this.serviceName = serviceName;
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

    public Builder putCalendarDate(DayOfWeek dayOfWeek, Boolean isSupported) {
      this.calendarDateMap.put(dayOfWeek, isSupported);
      return this;
    }

    public Calendar build() {
      return new Calendar(this);
    }
  }
}
