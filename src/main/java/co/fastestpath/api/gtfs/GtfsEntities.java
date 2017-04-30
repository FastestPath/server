package co.fastestpath.api.gtfs;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class GtfsEntities {

  private final Map<GtfsEntityType, GtfsEntity> entities;

  public GtfsEntities(Map<GtfsEntityType, GtfsEntity> entities) {
    this.entities = ImmutableMap.copyOf(entities);
  }

  public Map<GtfsEntityType, GtfsEntity> getEntities() {
    return entities;
  }
}
