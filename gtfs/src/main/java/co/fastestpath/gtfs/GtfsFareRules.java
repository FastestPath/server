package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsFareRules.Builder.class)
public class GtfsFareRules implements GtfsEntity {

  private final String fareId;

  private final String routeId;

  private final String originId;

  private final String destinationId;

  private final String containsId;

  private GtfsFareRules(Builder builder) {
    fareId = builder.fareId;
    routeId = builder.routeId;
    originId = builder.originId;
    destinationId = builder.destinationId;
    containsId = builder.containsId;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("fare_id")
    private String fareId;

    @JsonProperty("route_id")
    private String routeId;

    @JsonProperty("origin_id")
    private String originId;

    @JsonProperty("destination_id")
    private String destinationId;

    @JsonProperty("contains_id")
    private String containsId;

    private Builder() {}

    public Builder fareId(String fareId) {
      this.fareId = fareId;
      return this;
    }

    public Builder routeId(String routeId) {
      this.routeId = routeId;
      return this;
    }

    public Builder originId(String originId) {
      this.originId = originId;
      return this;
    }

    public Builder destinationId(String destinationId) {
      this.destinationId = destinationId;
      return this;
    }

    public Builder containsId(String containsId) {
      this.containsId = containsId;
      return this;
    }

    public GtfsFareRules build() {
      return new GtfsFareRules(this);
    }
  }
}
