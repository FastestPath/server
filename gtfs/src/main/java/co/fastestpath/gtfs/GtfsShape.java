package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsShape.Builder.class)
public class GtfsShape implements GtfsEntity {

  private final String id;

  private final String latitude;

  private final String longitude;

  private final String sequence;

  private final String distanceTraveled;

  private GtfsShape(Builder builder) {
    id = builder.id;
    latitude = builder.latitude;
    longitude = builder.longitude;
    sequence = builder.sequence;
    distanceTraveled = builder.distanceTraveled;
  }

  public String getId() {
    return id;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getSequence() {
    return sequence;
  }

  public String getDistanceTraveled() {
    return distanceTraveled;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("shape_id")
    private String id;

    @JsonProperty("shape_pt_lat")
    private String latitude;

    @JsonProperty("shape_pt_lon")
    private String longitude;

    @JsonProperty("shape_pt_sequence")
    private String sequence;

    @JsonProperty("shape_dist_traveled")
    private String distanceTraveled;

    private Builder() {}

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder latitude(String latitude) {
      this.latitude = latitude;
      return this;
    }

    public Builder longitude(String longitude) {
      this.longitude = longitude;
      return this;
    }

    public Builder sequence(String sequence) {
      this.sequence = sequence;
      return this;
    }

    public Builder distanceTraveled(String distanceTraveled) {
      this.distanceTraveled = distanceTraveled;
      return this;
    }

    public GtfsShape build() {
      return new GtfsShape(this);
    }
  }
}
