package co.fastestpath.api.schedule;

public class Schedule {

  private final Calendar calendar;

  private final TripMap trips;

  private final StopMap stops;

  private final StopTimeMap stopTimes;

  private final RouteMap routes;

  private Schedule(Builder builder) {
    calendar = builder.calendar;
    trips = builder.trips;
    stops = builder.stops;
    stopTimes = builder.stopTimes;
    routes = builder.routes;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Calendar calendar;
    private TripMap trips;
    private StopMap stops;
    private StopTimeMap stopTimes;
    private RouteMap routes;

    private Builder() {}

    public Builder calendar(Calendar calendar) {
      this.calendar = calendar;
      return this;
    }

    public Builder trips(TripMap trips) {
      this.trips = trips;
      return this;
    }

    public Builder stops(StopMap stops) {
      this.stops = stops;
      return this;
    }

    public Builder stopTimes(StopTimeMap stopTimes) {
      this.stopTimes = stopTimes;
      return this;
    }

    public Builder routes(RouteMap routes) {
      this.routes = routes;
      return this;
    }

    public Schedule build() {
      return new Schedule(this);
    }
  }
}

