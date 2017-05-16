package co.fastestpath.api.gtfs;

import co.fastestpath.api.gtfs.GtfsTrip.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsTrip implements GtfsEntity {

  private final String id;

  private final String routeId;

  private final String serviceId;

  private final String headSign;

  private final String shortName;

  private final Integer directionId;

  private final String blockId;

  private final String shapeId;

  private final Integer wheelchairAccessible;

  private final Integer bikesAllowed;

  private GtfsTrip(Builder builder) {
    id = builder.id;
    routeId = builder.routeId;
    serviceId = builder.serviceId;
    headSign = builder.headSign;
    shortName = builder.shortName;
    directionId = builder.directionId;
    blockId = builder.blockId;
    shapeId = builder.shapeId;
    wheelchairAccessible = builder.wheelchairAccessible;
    bikesAllowed = builder.bikesAllowed;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public String getRouteId() {
    return routeId;
  }

  public String getServiceId() {
    return serviceId;
  }

  public String getHeadSign() {
    return headSign;
  }

  public String getShortName() {
    return shortName;
  }

  public Integer getDirectionId() {
    return directionId;
  }

  public String getBlockId() {
    return blockId;
  }

  public String getShapeId() {
    return shapeId;
  }

  public Integer getWheelchairAccessible() {
    return wheelchairAccessible;
  }

  public Integer getBikesAllowed() {
    return bikesAllowed;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {
    @JsonProperty("trip_id")
    private String id;
    @JsonProperty("route_id")
    private String routeId;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("trip_headsign")
    private String headSign;
    @JsonProperty("trip_short_name")
    private String shortName;
    @JsonProperty("direction_id")
    private Integer directionId;
    @JsonProperty("block_id")
    private String blockId;
    @JsonProperty("shape_id")
    private String shapeId;
    @JsonProperty("wheelchair_accessible")
    private Integer wheelchairAccessible;
    @JsonProperty("bikes_allowed")
    private Integer bikesAllowed;

    private Builder() {}

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder routeId(String routeId) {
      this.routeId = routeId;
      return this;
    }

    public Builder serviceId(String serviceId) {
      this.serviceId = serviceId;
      return this;
    }

    public Builder headSign(String headSign) {
      this.headSign = headSign;
      return this;
    }

    public Builder shortName(String shortName) {
      this.shortName = shortName;
      return this;
    }

    public Builder directionId(Integer directionId) {
      this.directionId = directionId;
      return this;
    }

    public Builder blockId(String blockId) {
      this.blockId = blockId;
      return this;
    }

    public Builder shapeId(String shapeId) {
      this.shapeId = shapeId;
      return this;
    }

    public Builder wheelchairAccessible(Integer wheelchairAccessible) {
      this.wheelchairAccessible = wheelchairAccessible;
      return this;
    }

    public Builder bikesAllowed(Integer bikesAllowed) {
      this.bikesAllowed = bikesAllowed;
      return this;
    }

    public GtfsTrip build() {
      return new GtfsTrip(this);
    }
  }
}
