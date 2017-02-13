package co.fastestpath.api.schedule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

public class Station {

  @JsonUnwrapped
  private final StationName name;

  @JsonIgnore
  private final Map<String, Stop> stopIdMap;

  private Station(Builder builder) {
    name = builder.name;
    stopIdMap = builder.stopIdMap;
  }

  public StationName getName() {
    return name;
  }

  public Map<String, Stop> getStopIdMap() {
    return stopIdMap;
  }

  public static final class Builder {
    private StationName name;
    private Map<String, Stop> stopIdMap;

    public Builder() {
    }

    public Builder name(StationName name) {
      this.name = name;
      return this;
    }

    public Builder stopIdMap(Map<String, Stop> stopIdMap) {
      this.stopIdMap = stopIdMap;
      return this;
    }

    public Station build() {
      return new Station(this);
    }
  }
}

