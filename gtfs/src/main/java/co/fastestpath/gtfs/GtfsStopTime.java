package co.fastestpath.gtfs;

import co.fastestpath.gtfs.GtfsStopTime.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsStopTime implements GtfsEntity {

  private final String tripId;

  private final String arrivalTime;

  private final String departureTime;

  private final String stopId;

  private final int stopSequence;

  private final String stopHeadSign;

  private final Integer pickupType;

  private final Integer dropOffType;

  private final Float shapeDistTraveled;
  
  private final GtfsTimePointType timePoint;

  public static Builder builder() {
    return new Builder();
  }

  private GtfsStopTime(Builder builder) {
    tripId = builder.tripId;
    arrivalTime = builder.arrivalTime;
    departureTime = builder.departureTime;
    stopId = builder.stopId;
    stopSequence = builder.stopSequence;
    stopHeadSign = builder.stopHeadSign;
    pickupType = builder.pickupType;
    dropOffType = builder.dropOffType;
    shapeDistTraveled = builder.shapeDistTraveled;
    timePoint = builder.timePoint;
  }

  public String getTripId() {
    return tripId;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public String getStopId() {
    return stopId;
  }

  public int getStopSequence() {
    return stopSequence;
  }

  public String getStopHeadSign() {
    return stopHeadSign;
  }

  public Integer getPickupType() {
    return pickupType;
  }

  public Integer getDropOffType() {
    return dropOffType;
  }

  public Float getShapeDistTraveled() {
    return shapeDistTraveled;
  }

  public GtfsTimePointType getTimePoint() {
    return timePoint;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("stop_sequence")
    private int stopSequence;

    @JsonProperty("stop_headsign")
    private String stopHeadSign;

    @JsonProperty("pickup_type")
    private Integer pickupType;

    @JsonProperty("drop_off_type")
    private Integer dropOffType;

    @JsonProperty("shape_dist_traveled")
    private Float shapeDistTraveled;

    @JsonProperty("timepoint")
    private GtfsTimePointType timePoint;

    public Builder() {
    }

    public Builder tripId(String tripId) {
      this.tripId = tripId;
      return this;
    }

    public Builder arrivalTime(String arrivalTime) {
      this.arrivalTime = arrivalTime;
      return this;
    }

    public Builder departureTime(String departureTime) {
      this.departureTime = departureTime;
      return this;
    }

    public Builder stopId(String stopId) {
      this.stopId = stopId;
      return this;
    }

    public Builder stopSequence(int stopSequence) {
      this.stopSequence = stopSequence;
      return this;
    }

    public Builder stopHeadSign(String stopHeadSign) {
      this.stopHeadSign = stopHeadSign;
      return this;
    }

    public Builder pickupType(Integer pickupType) {
      this.pickupType = pickupType;
      return this;
    }

    public Builder dropOffType(Integer dropOffType) {
      this.dropOffType = dropOffType;
      return this;
    }

    public Builder shapeDistTraveled(Float shapeDistTraveled) {
      this.shapeDistTraveled = shapeDistTraveled;
      return this;
    }

    public Builder timePoint(GtfsTimePointType timePoint) {
      this.timePoint = timePoint;
      return this;
    }

    public GtfsStopTime build() {
      return new GtfsStopTime(this);
    }
  }
}
