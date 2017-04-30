package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.models.GtfsHhMmSs;
import co.fastestpath.api.gtfs.GtfsHhMmSsFactory;
import co.fastestpath.api.schedule.models.StationName;
import co.fastestpath.api.schedule.models.Trip;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class Schedule {


  private static final Logger LOG = LoggerFactory.getLogger(Schedule.class);

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
        .filter((departure) -> isAfterDesiredDepartureTime(departure, departAt)) // TODO: this comparator should be variable
        .filter((departure) -> isDestinationLastStop(departure, to))
        .min(Comparator.comparing(Trip::getDuration));
  }

  public Instant getModifiedOn() {
    return modifiedOn;
  }

  /**
   * The provided schedules don't include dates, only hours, minutes, seconds.
   */
  private static boolean isAfterDesiredDepartureTime(Trip departure, Instant desiredDeparture) {
    // TODO: how does the schedule indicate weekends?
    GtfsHhMmSs desiredHhMmSs = GtfsHhMmSsFactory.create(desiredDeparture);
    GtfsHhMmSs departureHhMmSs = departure.getDeparture()
        .getDepartureTime();

    return departureHhMmSs.compareTo(desiredHhMmSs) == 1;
  }

  private static boolean isDestinationLastStop(Trip departure, StationName destination) {
    return departure.getArrival()
        .getStation().getName()
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

