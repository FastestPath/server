package co.fastestpath.api.schedule.calendar;

import co.fastestpath.api.schedule.ServiceId;
import co.fastestpath.gtfs.GtfsCalendarDateExceptionType;

public class CalendarExceptionDate {

  private final ServiceId serviceId;

  private final CalendarDate date;

  private GtfsCalendarDateExceptionType exceptionType;

  private CalendarExceptionDate(Builder builder) {
    serviceId = builder.serviceId;
    date = builder.date;
    exceptionType = builder.exceptionType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public CalendarDate getDate() {
    return date;
  }

  public GtfsCalendarDateExceptionType getExceptionType() {
    return exceptionType;
  }

  public static final class Builder {
    private ServiceId serviceId;
    private CalendarDate date;
    private GtfsCalendarDateExceptionType exceptionType;

    private Builder() {
    }

    public Builder serviceId(ServiceId serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder date(CalendarDate date) {
      this.date = date;
      return this;
    }

    public Builder exceptionType(GtfsCalendarDateExceptionType exceptionType) {
      this.exceptionType = exceptionType;
      return this;
    }

    public CalendarExceptionDate build() {
      return new CalendarExceptionDate(this);
    }
  }
}
