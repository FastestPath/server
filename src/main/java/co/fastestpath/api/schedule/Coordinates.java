package co.fastestpath.api.schedule;

public class Coordinates {

  private final float latitude;

  private final float longitude;

  private Coordinates(Builder builder) {
    latitude = builder.latitude;
    longitude = builder.longitude;
  }

  public static Builder builder() {
    return new Builder();
  }

  public float getLatitude() {
    return latitude;
  }

  public float getLongitude() {
    return longitude;
  }

  public static final class Builder {
    private float latitude;
    private float longitude;

    private Builder() {
    }

    public Builder latitude(float latitude) {
      this.latitude = latitude;
      return this;
    }

    public Builder longitude(float longitude) {
      this.longitude = longitude;
      return this;
    }

    public Coordinates build() {
      return new Coordinates(this);
    }
  }
}
