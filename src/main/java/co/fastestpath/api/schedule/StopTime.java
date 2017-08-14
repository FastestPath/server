package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.trip.TripId;

// TODO: test comparable
public class StopTime implements Comparable<StopTime> {

  private final TripId tripId;

  private final HhMmSs arrivalTime;

  private final HhMmSs departureTime;

  private final StopId stopId;

  private final Integer sequence;

  private final String headsign;

  private final Integer pickupType;

  private final Integer dropOffType;

  private final Float shapeDistTraveled;

  private final TimePoint timePoint;

  private StopTime(Builder builder) {
    tripId = builder.tripId;
    arrivalTime = builder.arrivalTime;
    departureTime = builder.departureTime;
    stopId = builder.stopId;
    sequence = builder.sequence;
    headsign = builder.headsign;
    pickupType = builder.pickupType;
    dropOffType = builder.dropOffType;
    shapeDistTraveled = builder.shapeDistTraveled;
    timePoint = builder.timePoint;
  }

  public static Builder builder() {
    return new Builder();
  }

  public TripId getTripId() {
    return tripId;
  }

  public HhMmSs getArrivalTime() {
    return arrivalTime;
  }

  public HhMmSs getDepartureTime() {
    return departureTime;
  }

  public StopId getStopId() {
    return stopId;
  }

  public Integer getSequence() {
    return sequence;
  }

  public String getHeadsign() {
    return headsign;
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

  public TimePoint getTimePoint() {
    return timePoint;
  }

  @Override
  public int compareTo(StopTime o) {
    return sequence.compareTo(o.sequence);
  }

  public static final class Builder {
    private TripId tripId;
    private HhMmSs arrivalTime;
    private HhMmSs departureTime;
    private StopId stopId;
    private Integer sequence;
    private String headsign;
    private Integer pickupType;
    private Integer dropOffType;
    private Float shapeDistTraveled;
    private TimePoint timePoint;

    private Builder() {
    }

    public Builder tripId(TripId tripId) {
      this.tripId = tripId;
      return this;
    }

    public Builder arrivalTime(HhMmSs arrivalTime) {
      this.arrivalTime = arrivalTime;
      return this;
    }

    public Builder departureTime(HhMmSs departureTime) {
      this.departureTime = departureTime;
      return this;
    }

    public Builder stopId(StopId stopId) {
      this.stopId = stopId;
      return this;
    }

    public Builder sequence(int sequence) {
      this.sequence = sequence;
      return this;
    }

    public Builder headsign(String headsign) {
      this.headsign = headsign;
      return this;
    }

    public Builder pickupType(int pickupType) {
      this.pickupType = pickupType;
      return this;
    }

    public Builder dropOffType(int dropOffType) {
      this.dropOffType = dropOffType;
      return this;
    }

    public Builder shapeDistTraveled(Float shapeDistTraveled) {
      this.shapeDistTraveled = shapeDistTraveled;
      return this;
    }

    public Builder timePoint(TimePoint timePoint) {
      this.timePoint = timePoint;
      return this;
    }

    public StopTime build() {
      return new StopTime(this);
    }
  }
}
