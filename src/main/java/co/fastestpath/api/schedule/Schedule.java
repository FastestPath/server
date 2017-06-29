package co.fastestpath.api.schedule;

import java.time.ZoneId;

public class Schedule {

  private final ZoneId timeZone;

  private final AgencyMap agencies;

  private final TripMap trips;

  private final StopMap stops;

  private final StopTimeMap stopTimes;

  private final StopTripMap stopTripMap;

  private final RouteMap routes;

  private final CalendarMap calendars;

  private Schedule(Builder builder) {
    timeZone = builder.timeZone;
    agencies = builder.agencies;
    calendars = builder.calendars;
    trips = builder.trips;
    stops = builder.stops;
    stopTimes = builder.stopTimes;
    routes = builder.routes;
  }

  public static Builder builder() {
    return new Builder();
  }

  public ZoneId getTimeZone() {
    return timeZone;
  }

  public AgencyMap getAgencies() {
    return agencies;
  }

  public TripMap getTrips() {
    return trips;
  }

  public StopMap getStops() {
    return stops;
  }

  public StopTimeMap getStopTimes() {
    return stopTimes;
  }

  public StopTripMap getStopTrips() {
    return stopTripMap;
  }

  public RouteMap getRoutes() {
    return routes;
  }

  public CalendarMap getCalendars() {
    return calendars;
  }

  public static final class Builder {
    private ZoneId timeZone;
    private AgencyMap agencies;
    private TripMap trips;
    private StopMap stops;
    private StopTimeMap stopTimes;
    private RouteMap routes;
    private CalendarMap calendars;

    private Builder() {}

    public Builder timeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
      return this;
    }

    public Builder agencies(AgencyMap agencies) {
      this.agencies = agencies;
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

    public Builder calendar(CalendarMap calendars) {
      this.calendars = calendars;
      return this;
    }

    public Schedule build() {
      return new Schedule(this);
    }
  }
}

