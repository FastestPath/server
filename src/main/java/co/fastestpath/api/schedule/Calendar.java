package co.fastestpath.api.schedule;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * First checks for `calendar_dates` then falls back to `calendar`.
 * Links to {@link Trip} via {@link ServiceId}
 */
public class Calendar {

  private final ServiceId serviceId;

  private final Map<CalendarDate, CalendarExceptionDate> exceptionDates;

  private Calendar(Builder builder) {
    serviceId = builder.serviceId;
    exceptionDates = ImmutableMap.copyOf(builder.exceptionDates);
  }

  public static Builder builder() {
    return new Builder();
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public Map<CalendarDate, CalendarExceptionDate> getExceptionDates() {
    return exceptionDates;
  }

  public static final class Builder {

    private ServiceId serviceId;

    private Map<CalendarDate, CalendarExceptionDate> exceptionDates;

    private Builder() {}

    public Builder serviceId(ServiceId serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder exceptionDates(Map<CalendarDate, CalendarExceptionDate> exceptionDates) {
      this.exceptionDates = exceptionDates;
      return this;
    }

    public Calendar build() {
      return new Calendar(this);
    }
  }
}
