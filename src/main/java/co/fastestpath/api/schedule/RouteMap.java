package co.fastestpath.api.schedule;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class RouteMap {

  private final Map<RouteId, Route> map;

  private RouteMap(Builder builder) {
    map = ImmutableMap.copyOf(builder.map);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Route get(RouteId routeId) {
    return map.get(routeId);
  }

  public static final class Builder {

    private Map<RouteId, Route> map = new HashMap<>();

    private Builder() {}

    public Builder put(RouteId routeId, Route route) {
      this.map.put(routeId, route);
      return this;
    }

    public RouteMap build() {
      return new RouteMap(this);
    }
  }
}
