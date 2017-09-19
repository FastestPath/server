package co.fastestpath.gtfs.models;

import co.fastestpath.gtfs.models.GtfsCalendarDate.Builder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsCalendarDate implements co.fastestpath.api.gtfs.GtfsEntity {

  private final String serviceId;

  private final String date;

  private final String holidayName;

  private final co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType exceptionType;

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

  public String getDate() {
    return date;
  }

  public String getHolidayName() {
    return holidayName;
  }

  public co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType getExceptionType() {
    return exceptionType;
  }

  @JsonPOJOBuilder(withPrefix = "")
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static final class Builder {

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("date")
    private String date;

    @JsonProperty("holiday_name")
    private String holidayName;

    @JsonProperty("exception_type")
    private co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType exceptionType;

    private Builder() {}

    public Builder serviceId(String serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder date(String date) {
      this.date = date;
      return this;
    }

    public Builder holidayName(String holidayName) {
      this.holidayName = holidayName;
      return this;
    }

    public Builder exceptionType(co.fastestpath.api.gtfs.GtfsCalendarDateExceptionType exceptionType) {
      this.exceptionType = exceptionType;
      return this;
    }

    public GtfsCalendarDate build() {
      return new GtfsCalendarDate(this);
    }
  }
}
