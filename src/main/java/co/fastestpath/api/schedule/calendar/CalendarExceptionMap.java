package co.fastestpath.api.schedule.calendar;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CalendarExceptionMap {

  private final Map<String, CalendarExceptionDate> map;

  private CalendarExceptionMap(Builder builder) {
    map = ImmutableMap.copyOf(builder.map);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Optional<CalendarExceptionDate> get(CalendarDate date) {
    String yyyyMMdd = date.toString();
    return Optional.ofNullable(map.get(yyyyMMdd));
  }

  public static final class Builder {

    private Map<String, CalendarExceptionDate> map = new HashMap<>();

    private Builder() {}

    public Builder put(CalendarExceptionDate exceptionDate) {
      map.put(exceptionDate.getDate().toString(), exceptionDate);
      return this;
    }

    public CalendarExceptionMap build() {
      return new CalendarExceptionMap(this);
    }
  }
}
