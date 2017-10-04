package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsFrequency.Builder.class)
public class GtfsFrequency implements GtfsEntity {

  private final String tripId;

  private final String startTime;

  private final String endTime;

  private final String headwaySeconds;

  private final GtfsFrequencyTimeType timeType;

  private GtfsFrequency(Builder builder) {
    tripId = builder.tripId;
    startTime = builder.startTime;
    endTime = builder.endTime;
    headwaySeconds = builder.headwaySeconds;
    timeType = builder.timeType;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("headway_secs")
    private String headwaySeconds;

    @JsonProperty("exact_times")
    private GtfsFrequencyTimeType timeType;

    private Builder() {}

    public Builder tripId(String tripId) {
      this.tripId = tripId;
      return this;
    }

    public Builder startTime(String startTime) {
      this.startTime = startTime;
      return this;
    }

    public Builder endTime(String endTime) {
      this.endTime = endTime;
      return this;
    }

    public Builder headwaySeconds(String headwaySeconds) {
      this.headwaySeconds = headwaySeconds;
      return this;
    }

    public Builder timeType(GtfsFrequencyTimeType timeType) {
      this.timeType = timeType;
      return this;
    }

    public GtfsFrequency build() {
      return new GtfsFrequency(this);
    }
  }
}
