package co.fastestpath.api.schedule;

import com.google.inject.Provides;

import javax.inject.Singleton;
import java.time.ZoneId;

@Singleton
public class Schedule {

  private static ScheduleInstance instance;

  private static ScheduleInstance getInstance(String desiredField) {
    if (instance == null) {
      throw new RuntimeException("Unable to provide " + desiredField + ". Schedule has not been set yet.");
    }
    return instance;
  }

  public ScheduleInstance.Builder clear() {
    instance = null;
    return new ScheduleInstance.Builder();
  }

  @Provides
  public ZoneId getTimeZone() {
    return getInstance("time zone").timeZone;
  }

  @Provides
  public AgencyMap getAgencies() {
    return getInstance("agencies").agencies;
  }

  @Provides
  public TripMap getTrips() {
    return getInstance("trips").trips;
  }

  @Provides
  public StopMap getStops() {
    return getInstance("stops").stops;
  }

  @Provides
  public StopTimeMap getStopTimes() {
    return getInstance("stop times").stopTimes;
  }

  @Provides
  public StopTripMap getStopTrips() {
    return getInstance("stop trips").stopTripMap;
  }

  @Provides
  public RouteMap getRoutes() {
    return getInstance("routes").routes;
  }

  @Provides
  public CalendarMap getCalendars() {
    return getInstance("calendars").calendars;
  }

  static class ScheduleInstance {

    private final ZoneId timeZone;

    private final AgencyMap agencies;

    private final TripMap trips;

    private final StopMap stops;

    private final StopTimeMap stopTimes;

    private final StopTripMap stopTripMap;

    private final RouteMap routes;

    private final CalendarMap calendars;

    private ScheduleInstance(Builder builder) {
      timeZone = builder.timeZone;
      agencies = builder.agencies;
      calendars = builder.calendars;
      trips = builder.trips;
      stops = builder.stops;
      stopTimes = builder.stopTimes;
      stopTripMap = builder.stopTripMap;
      routes = builder.routes;
    }

    public static final class Builder {
      private ZoneId timeZone;
      private AgencyMap agencies;
      private TripMap trips;
      private StopMap stops;
      private StopTimeMap stopTimes;
      private StopTripMap stopTripMap;
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

      public Builder stopTripMap(StopTripMap stopTripMap) {
        this.stopTripMap = stopTripMap;
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

      public void set() {
        instance = new ScheduleInstance(this);
      }
    }
  }
}

