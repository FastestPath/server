package co.fastestpath.api.schedule.calendar;

import com.google.common.collect.ImmutableMap;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class DayOfWeekMap {

  private final Map<DayOfWeek, Boolean> map;

  private DayOfWeekMap(Builder builder) {
    map = ImmutableMap.copyOf(builder.map);
  }

  public static Builder builder() {
    return new Builder();
  }

  public boolean isDaySupported(DayOfWeek dayOfWeek) {
    Boolean isSupported = map.get(dayOfWeek);
    return isSupported != null && isSupported;
  }

  public static final class Builder {

    private Map<DayOfWeek, Boolean> map = new HashMap<>();

    private Builder() {}

    public Builder put(DayOfWeek dayOfWeek, boolean isSupported) {
      map.put(dayOfWeek, isSupported);
      return this;
    }

    public DayOfWeekMap build() {
      return new DayOfWeekMap(this);
    }
  }
}
