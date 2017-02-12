package co.fastestpath.api.schedule.models;

import co.fastestpath.api.schedule.StationNotPresentException;
import com.google.common.collect.Multimap;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class Schedule {

  private final Multimap<StationName, Trip> departureMap;

  // TODO: arrivalMap

  private final Instant modifiedOn;

  private Schedule(Builder builder) {
    departureMap = builder.departureMap;
    modifiedOn = builder.modifiedOn;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Optional<Trip> getTripForDepartureTime(StationName from, StationName to, Instant departAt) {
    Collection<Trip> trips = departureMap.get(from);

    if (trips == null) {
      throw new StationNotPresentException("Station not present, StationName = " + from);
    }

    return trips.stream()
        .filter((departure) -> isAfterDesiredDepartureTime(departure, departAt))
        .filter((departure) -> isDestinationLastStop(departure, to))
        .findFirst();
  }

  public Instant getModifiedOn() {
    return modifiedOn;
  }

  /**
   * The provided schedules don't include dates, only hours, minutes, seconds.
   */
  private static boolean isAfterDesiredDepartureTime(Trip departure, Instant desiredDeparture) {
    Date departureDate = Date.from(departure.getDepartureTime());

    // TODO: how does the schedule indicate weekends?
    // make sure we sync up dates to use the same day
    Date desiredDepartureDate = Date.from(desiredDeparture);
    desiredDepartureDate.setDate(departureDate.getDate());

    return departureDate.toInstant()
        .isAfter(desiredDepartureDate.toInstant());
  }

  private static boolean isDestinationLastStop(Trip departure, StationName destination) {
    return departure.getArrivalStation().getName()
        .equals(destination);
  }

  public static final class Builder {
    private Multimap<StationName, Trip> departureMap;
    private Instant modifiedOn;

    private Builder() {
    }

    public Builder departureMap(Multimap<StationName, Trip> departureMap) {
      this.departureMap = departureMap;
      return this;
    }

    public Builder modifiedOn(Instant modifiedOn) {
      this.modifiedOn = modifiedOn;
      return this;
    }

    public Schedule build() {
      return new Schedule(this);
    }
  }
}

