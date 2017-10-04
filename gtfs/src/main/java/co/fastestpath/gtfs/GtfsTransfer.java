package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsTransfer.Builder.class)
public class GtfsTransfer implements GtfsEntity {

  private final String fromStopId;

  private final String toStopId;

  private final GtfsTransferType type;

  private final String minimumTransferTime;

  private GtfsTransfer(Builder builder) {
    fromStopId = builder.fromStopId;
    toStopId = builder.toStopId;
    type = builder.type;
    minimumTransferTime = builder.minimumTransferTime;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("from_stop_id")
    private String fromStopId;

    @JsonProperty("to_stop_id")
    private String toStopId;

    @JsonProperty("transfer_type")
    private GtfsTransferType type;

    @JsonProperty("min_transfer_time")
    private String minimumTransferTime;

    private Builder() {}

    public Builder fromStopId(String fromStopId) {
      this.fromStopId = fromStopId;
      return this;
    }

    public Builder toStopId(String toStopId) {
      this.toStopId = toStopId;
      return this;
    }

    public Builder type(GtfsTransferType type) {
      this.type = type;
      return this;
    }

    public Builder minimumTransferTime(String minimumTransferTime) {
      this.minimumTransferTime = minimumTransferTime;
      return this;
    }

    public GtfsTransfer build() {
      return new GtfsTransfer(this);
    }
  }
}
