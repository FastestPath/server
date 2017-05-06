package co.fastestpath.api.gtfs;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtfsEntityMap {

  private final Map<GtfsEntityType, List<GtfsEntity>> entities;

  public GtfsEntityMap(Map<GtfsEntityType, List<GtfsEntity>> entities) {
    this.entities = ImmutableMap.copyOf(entities);
  }

  private GtfsEntityMap(Builder builder) {
    entities = ImmutableMap.copyOf(builder.entities);
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<? extends GtfsEntity> get(GtfsEntityType entityType) {
    return entities.get(entityType);
  }

  public static final class Builder {

    private Map<GtfsEntityType, List<GtfsEntity>> entities = new HashMap<>();

    private Builder() {}

    public Builder put(GtfsEntityType entityType, List<GtfsEntity> entities) {
      this.entities.put(entityType, ImmutableList.copyOf(entities));
      return this;
    }

    public GtfsEntityMap build() {
      return new GtfsEntityMap(this);
    }
  }
}
