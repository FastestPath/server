package co.fastestpath.api.schedule;

public class Trip {

  private final TripId id;

  private final RouteId routeId;

  private final ServiceId serviceId;

  private final String headsign;

  private final String shortName;

  private final String directionId;

  private final String blockId;

  private final ShapeId shapeId;

  private Trip(Builder builder) {
    id = builder.id;
    routeId = builder.routeId;
    serviceId = builder.serviceId;
    headsign = builder.headsign;
    shortName = builder.shortName;
    directionId = builder.directionId;
    blockId = builder.blockId;
    shapeId = builder.shapeId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public TripId getId() {
    return id;
  }

  public RouteId getRouteId() {
    return routeId;
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public String getHeadsign() {
    return headsign;
  }

  public String getShortName() {
    return shortName;
  }

  public String getDirectionId() {
    return directionId;
  }

  public String getBlockId() {
    return blockId;
  }

  public ShapeId getShapeId() {
    return shapeId;
  }

  public static final class Builder {
    private TripId id;
    private RouteId routeId;
    private ServiceId serviceId;
    private String headsign;
    private String shortName;
    private String directionId;
    private String blockId;
    private ShapeId shapeId;

    private Builder() {
    }

    public Builder id(TripId id) {
      this.id = id;
      return this;
    }

    public Builder routeId(RouteId routeId) {
      this.routeId = routeId;
      return this;
    }

    public Builder serviceId(ServiceId serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder headsign(String headsign) {
      this.headsign = headsign;
      return this;
    }

    public Builder shortName(String shortName) {
      this.shortName = shortName;
      return this;
    }

    public Builder directionId(String directionId) {
      this.directionId = directionId;
      return this;
    }

    public Builder blockId(String blockId) {
      this.blockId = blockId;
      return this;
    }

    public Builder shapeId(ShapeId shapeId) {
      this.shapeId = shapeId;
      return this;
    }

    public Trip build() {
      return new Trip(this);
    }
  }
}
