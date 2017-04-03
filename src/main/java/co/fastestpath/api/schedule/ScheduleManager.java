package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.Trip;
import co.fastestpath.api.schedule.models.Departure;
import co.fastestpath.api.schedule.models.Schedule;
import co.fastestpath.api.schedule.models.StationName;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Function;

@Singleton
public class ScheduleManager {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduleManager.class);

  private final ScheduleFetcher scheduleFetcher;

	private Schedule schedule; // storing the latest schedule in memory for now

  private boolean isFetching;

	@Inject
	public ScheduleManager(ScheduleFetcher scheduleFetcher) {
		this.scheduleFetcher = scheduleFetcher;
	}

	public void fetchLatest() {
	  fetchLatest(null);
  }

	public void fetchLatest(ScheduleFetchCallback callback) {
    if (isFetching) {
      LOG.info("Unable to fetch schedule, a fetch is already in progress.");
      return;
    }

    this.isFetching = true;
    Instant currentModifiedOn = schedule == null ? null : schedule.getModifiedOn();
    Optional<Schedule> latest;
    try {
      latest = scheduleFetcher.fetchSchedule(currentModifiedOn);
    } catch (ScheduleFetcherException e) {
      LOG.error("Unable to fetch schedule.", e);
      return;
    } catch (ScheduleParseException e) {
      LOG.error("Unable to parse schedule.", e);
      return;
    } finally {
      this.isFetching = false;
    }

    // latest will be empty if up-to-date
    latest.ifPresent(schedule -> this.schedule = schedule);

    if (callback != null) {
      callback.onFetch();
    }
  }

  public Optional<Departure> getDeparture(StationName from, StationName to, Instant departAt) {
    departAt = ObjectUtils.defaultIfNull(departAt, Instant.now());
    Optional<Trip> sequence = schedule.getTripForDepartureTime(from, to, departAt);

    if (!sequence.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(Departure.create(sequence.get()));
  }
}