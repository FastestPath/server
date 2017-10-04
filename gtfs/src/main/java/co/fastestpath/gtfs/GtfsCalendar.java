package co.fastestpath.gtfs;

import co.fastestpath.gtfs.GtfsCalendar.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(builder = Builder.class)
public class GtfsCalendar implements GtfsEntity {

  private final String serviceId;

  private final String serviceName;

  private final Set<DayOfWeek> daysOfWeek;

  private final String startDate;

  private final String endDate;

  private GtfsCalendar(Builder builder) {
    serviceId = builder.serviceId;
    serviceName = builder.serviceName;
    daysOfWeek = createSupportedDays(builder);
    startDate = builder.startDate;
    endDate = builder.endDate;
  }

  private static Set<DayOfWeek> createSupportedDays(Builder builder) {
    Set<DayOfWeek> supportedDays = new HashSet<>(7);
    if (builder.monday == 1) {
      supportedDays.add(DayOfWeek.MONDAY);
    }

    if (builder.tuesday == 1) {
      supportedDays.add(DayOfWeek.TUESDAY);
    }

    if (builder.wednesday == 1) {
      supportedDays.add(DayOfWeek.WEDNESDAY);
    }

    if (builder.thursday == 1) {
      supportedDays.add(DayOfWeek.THURSDAY);
    }

    if (builder.friday == 1) {
      supportedDays.add(DayOfWeek.FRIDAY);
    }

    if (builder.saturday == 1) {
      supportedDays.add(DayOfWeek.SATURDAY);
    }

    if (builder.sunday == 1) {
      supportedDays.add(DayOfWeek.SUNDAY);
    }

    return supportedDays;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getServiceId() {
    return serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public Set<DayOfWeek> getDaysOfWeek() {
    return daysOfWeek;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("monday")
    private int monday;

    @JsonProperty("tuesday")
    private int tuesday;

    @JsonProperty("wednesday")
    private int wednesday;

    @JsonProperty("thursday")
    private int thursday;

    @JsonProperty("friday")
    private int friday;

    @JsonProperty("saturday")
    private int saturday;

    @JsonProperty("sunday")
    private int sunday;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    private Builder() {}

    public Builder serviceId(String serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder serviceName(String serviceName) {
      this.serviceName = serviceName;
      return this;
    }

    public Builder monday(int monday) {
      this.monday = monday;
      return this;
    }

    public Builder tuesday(int tuesday) {
      this.tuesday = tuesday;
      return this;
    }

    public Builder wednesday(int wednesday) {
      this.wednesday = wednesday;
      return this;
    }

    public Builder thursday(int thursday) {
      this.thursday = thursday;
      return this;
    }

    public Builder friday(int friday) {
      this.friday = friday;
      return this;
    }

    public Builder saturday(int saturday) {
      this.saturday = saturday;
      return this;
    }

    public Builder sunday(int sunday) {
      this.sunday = sunday;
      return this;
    }

    public Builder startDate(String startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(String endDate) {
      this.endDate = endDate;
      return this;
    }

    public GtfsCalendar build() {
      return new GtfsCalendar(this);
    }
  }
}
