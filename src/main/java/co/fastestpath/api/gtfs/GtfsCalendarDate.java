package co.fastestpath.api.gtfs;

import co.fastestpath.api.gtfs.GtfsCalendarDate.Builder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.Instant;

@JsonDeserialize(builder = Builder.class)
public class GtfsCalendarDate implements GtfsEntity {

  private final String serviceId;

  private final Instant date;

  private final String holidayName;

  private final GtfsCalendarDateExceptionType exceptionType;

  private GtfsCalendarDate(Builder builder) {
    serviceId = builder.serviceId;
    date = builder.date;
    holidayName = builder.holidayName;
    exceptionType = builder.exceptionType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getServiceId() {
    return serviceId;
  }

  public Instant getDate() {
    return date;
  }

  public String getHolidayName() {
    return holidayName;
  }

  public GtfsCalendarDateExceptionType getExceptionType() {
    return exceptionType;
  }

  @JsonPOJOBuilder(withPrefix = "")
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static final class Builder {

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("date")
    private Instant date;

    @JsonProperty("holiday_name")
    private String holidayName;

    @JsonProperty("exception_type")
    private GtfsCalendarDateExceptionType exceptionType;

    private Builder() {}

    public Builder serviceId(String serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder date(Instant date) {
      this.date = date;
      return this;
    }

    public Builder holidayName(String holidayName) {
      this.holidayName = holidayName;
      return this;
    }

    public Builder exceptionType(GtfsCalendarDateExceptionType exceptionType) {
      this.exceptionType = exceptionType;
      return this;
    }

    public GtfsCalendarDate build() {
      return new GtfsCalendarDate(this);
    }
  }
}
