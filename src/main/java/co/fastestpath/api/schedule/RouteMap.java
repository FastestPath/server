package co.fastestpath.api.schedule;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class RouteMap {

  private final BiMap<RouteId, Route> map;

  public static RouteMap fromRoutes(Set<Route> routes) {
    BiMap<RouteId, Route> map = HashBiMap.create(routes.size());
    routes.forEach((route) -> map.put(route.getId(), route));
    return new RouteMap(map);
  }

  private RouteMap(BiMap<RouteId, Route> map) {
    this.map = ImmutableBiMap.copyOf(map);
  }

  public Route get(RouteId routeId) {
    return map.get(routeId);
  }

  public Set<Route> getAll() {
    return ImmutableSet.copyOf(map.values());
  }
}
