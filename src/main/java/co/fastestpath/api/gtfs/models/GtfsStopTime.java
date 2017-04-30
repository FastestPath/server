package co.fastestpath.api.gtfs.models;

import co.fastestpath.api.gtfs.models.GtfsStopTime.Builder;
import co.fastestpath.api.schedule.models.Station;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsStopTime {

  private final String tripId;

  private final GtfsHhMmSs arrivalTime;

  private final GtfsHhMmSs departureTime;

  private final String stopId;

  private final String stopSequence;

  @JsonIgnore
  private final String stopHeadSign;

  @JsonIgnore
  private final String pickupType;

  @JsonIgnore
  private final String dropOffType;

  private final String shapeDistTraveled;

  private Station station;

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
  }

  public String getTripId() {
    return tripId;
  }

  public GtfsHhMmSs getArrivalTime() {
    return arrivalTime;
  }

  public GtfsHhMmSs getDepartureTime() {
    return departureTime;
  }

  public String getStopId() {
    return stopId;
  }

  public String getStopSequence() {
    return stopSequence;
  }

  public String getStopHeadSign() {
    return stopHeadSign;
  }

  public String getPickupType() {
    return pickupType;
  }

  public String getDropOffType() {
    return dropOffType;
  }

  public String getShapeDistTraveled() {
    return shapeDistTraveled;
  }

  public Station getStation() {
    return station;
  }

  public void setStation(Station station) {
    this.station = station;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("arrival_time")
    private GtfsHhMmSs arrivalTime;

    @JsonProperty("departure_time")
    private GtfsHhMmSs departureTime;

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("stop_sequence")
    private String stopSequence;

    @JsonProperty("stop_headsign")
    private String stopHeadSign;

    @JsonProperty("pickup_type")
    private String pickupType;

    @JsonProperty("drop_off_type")
    private String dropOffType;

    @JsonProperty("shape_dist_traveled")
    private String shapeDistTraveled;

    public Builder() {
    }

    public Builder tripId(String val) {
      tripId = val;
      return this;
    }

    public Builder arrivalTime(String val) {
      arrivalTime = GtfsHhMmSs.create(val);
      return this;
    }

    public Builder departureTime(String val) {
      departureTime = GtfsHhMmSs.create(val);
      return this;
    }

    public Builder stopId(String val) {
      stopId = val;
      return this;
    }

    public Builder stopSequence(String val) {
      stopSequence = val;
      return this;
    }

    public Builder stopHeadSign(String val) {
      stopHeadSign = val;
      return this;
    }

    public Builder pickupType(String val) {
      pickupType = val;
      return this;
    }

    public Builder dropOffType(String val) {
      dropOffType = val;
      return this;
    }

    public Builder shapeDistTraveled(String val) {
      shapeDistTraveled = val;
      return this;
    }

    public GtfsStopTime build() {
      return new GtfsStopTime(this);
    }
  }
}
