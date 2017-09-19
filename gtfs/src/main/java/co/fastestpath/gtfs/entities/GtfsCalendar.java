package co.fastestpath.gtfs.models;

import co.fastestpath.gtfs.models.GtfsCalendar.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(builder = Builder.class)
public class GtfsCalendar implements GtfsEntity {

  private final String serviceId;

  private final String serviceName;

  private final Map<DayOfWeek, Boolean> daysOfWeek;

  private final String startDate;

  private final String endDate;

  private GtfsCalendar(Builder builder) {
    serviceId = builder.serviceId;
    serviceName = builder.serviceName;
    daysOfWeek = createValidDaysMap(builder);
    startDate = builder.startDate;
    endDate = builder.endDate;
  }

  private static Map<DayOfWeek, Boolean> createValidDaysMap(Builder builder) {
    Map<DayOfWeek, Boolean> daysOfWeekMap = new HashMap<>(7);
    daysOfWeekMap.put(DayOfWeek.MONDAY, builder.monday == 1);
    daysOfWeekMap.put(DayOfWeek.TUESDAY, builder.tuesday == 1);
    daysOfWeekMap.put(DayOfWeek.WEDNESDAY, builder.wednesday == 1);
    daysOfWeekMap.put(DayOfWeek.THURSDAY, builder.thursday == 1);
    daysOfWeekMap.put(DayOfWeek.FRIDAY, builder.friday == 1);
    daysOfWeekMap.put(DayOfWeek.SATURDAY, builder.saturday == 1);
    daysOfWeekMap.put(DayOfWeek.SUNDAY, builder.sunday == 1);
    return daysOfWeekMap;
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

  public Map<DayOfWeek, Boolean> getDaysOfWeek() {
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

    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
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
