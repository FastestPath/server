package co.fastestpath.api.schedule.models;

import co.fastestpath.api.gtfs.models.GtfsStop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

public class Station {

  @JsonUnwrapped
  private final StationName name;

  @JsonIgnore
  private final Map<String, GtfsStop> stopIdMap;

  private Station(Builder builder) {
    name = builder.name;
    stopIdMap = builder.stopIdMap;
  }

  public StationName getName() {
    return name;
  }

  public Map<String, GtfsStop> getStopIdMap() {
    return stopIdMap;
  }

  public static final class Builder {
    private StationName name;
    private Map<String, GtfsStop> stopIdMap;

    public Builder() {
    }

    public Builder name(StationName name) {
      this.name = name;
      return this;
    }

    public Builder stopIdMap(Map<String, GtfsStop> stopIdMap) {
      this.stopIdMap = stopIdMap;
      return this;
    }

    public Station build() {
      return new Station(this);
    }
  }
}

