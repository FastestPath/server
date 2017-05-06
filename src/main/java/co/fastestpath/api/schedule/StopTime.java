package co.fastestpath.api.schedule;

import java.time.Instant;

public class StopTime {

  private final TripId tripId;

  private final Instant arrivalTime;

  private final Instant departureTime;

  private final int sequence;

  private final String headsign;

  private final int pickupType;

  private final int dropOffType;

  private final float shapeDistTraveled;

  private final TimePoint timePoint;

  private StopTime(Builder builder) {
    tripId = builder.tripId;
    arrivalTime = builder.arrivalTime;
    departureTime = builder.departureTime;
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

  public Instant getArrivalTime() {
    return arrivalTime;
  }

  public Instant getDepartureTime() {
    return departureTime;
  }

  public int getSequence() {
    return sequence;
  }

  public String getHeadsign() {
    return headsign;
  }

  public int getPickupType() {
    return pickupType;
  }

  public int getDropOffType() {
    return dropOffType;
  }

  public float getShapeDistTraveled() {
    return shapeDistTraveled;
  }

  public TimePoint getTimePoint() {
    return timePoint;
  }

  public static final class Builder {
    private TripId tripId;
    private Instant arrivalTime;
    private Instant departureTime;
    private int sequence;
    private String headsign;
    private int pickupType;
    private int dropOffType;
    private float shapeDistTraveled;
    private TimePoint timePoint;

    private Builder() {
    }

    public Builder tripId(TripId tripId) {
      this.tripId = tripId;
      return this;
    }

    public Builder arrivalTime(Instant arrivalTime) {
      this.arrivalTime = arrivalTime;
      return this;
    }

    public Builder departureTime(Instant departureTime) {
      this.departureTime = departureTime;
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

    public Builder shapeDistTraveled(float shapeDistTraveled) {
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
