package co.fastestpath.api.gtfs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class GtfsCalendar {

  private final String serviceId;

  private final Map<DayOfWeek, Boolean> validDays;

  private final Instant startDate;

  private final Instant endDate;

  private GtfsCalendar(Builder builder) {
    serviceId = builder.serviceId;
    validDays = createValidDaysMap(builder);
    startDate = builder.startDate;
    endDate = builder.endDate;
  }

  private static Map<DayOfWeek, Boolean> createValidDaysMap(Builder builder) {
    Map<DayOfWeek, Boolean> validDaysMap = new HashMap<>(7);
    validDaysMap.put(DayOfWeek.MONDAY, builder.monday == 1);
    validDaysMap.put(DayOfWeek.TUESDAY, builder.tuesday == 1);
    validDaysMap.put(DayOfWeek.WEDNESDAY, builder.wednesday == 1);
    validDaysMap.put(DayOfWeek.THURSDAY, builder.thursday == 1);
    validDaysMap.put(DayOfWeek.FRIDAY, builder.friday == 1);
    validDaysMap.put(DayOfWeek.SATURDAY, builder.saturday == 1);
    validDaysMap.put(DayOfWeek.SUNDAY, builder.sunday == 1);
    return validDaysMap;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    @JsonProperty("service_id")
    private String serviceId;

    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
    private int sunday;

    @JsonProperty("start_date")
    private Instant startDate;
    @JsonProperty("end_date")
    private Instant endDate;

    private Builder() {
    }

    public Builder serviceId(String serviceId) {
      this.serviceId = serviceId;
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

    public Builder startDate(Instant startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(Instant endDate) {
      this.endDate = endDate;
      return this;
    }

    public GtfsCalendar build() {
      return new GtfsCalendar(this);
    }
  }
}
