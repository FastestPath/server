package co.fastestpath.api.gtfs;

import co.fastestpath.api.managed.FastestPathManaged;
import co.fastestpath.api.managed.FastestPathManagedPriority;
import co.fastestpath.api.schedule.Schedule;
import co.fastestpath.api.schedule.ScheduleManager;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.Duration;

import static co.fastestpath.api.FastestPathModule.FETCH_INTERVAL;

@Singleton
public class GtfsArchiveManager implements FastestPathManaged {

	private static final Logger LOG = LoggerFactory.getLogger(GtfsArchiveManager.class);

	private final Duration fetchInterval;

	private final GtfsArchiveFetchJobFactory fetchJobFactory;

	private final Scheduler scheduler;

	private final ScheduleManager scheduleManager;

	@Inject
  public GtfsArchiveManager(@Named(FETCH_INTERVAL) Duration fetchInterval, GtfsArchiveFetchJobFactory fetchJobFactory,
      Scheduler scheduler, ScheduleManager scheduleManager) {
	  this.fetchInterval = fetchInterval;
    this.fetchJobFactory = fetchJobFactory;
    this.scheduler = scheduler;
    this.scheduleManager = scheduleManager;
  }

  @Override
  public void start() throws Exception {

    GtfsArchiveFetchJob fetchJob = fetchJobFactory.createJob((int) fetchInterval.toHours(), (archive) -> {
      Schedule schedule = ScheduleFactory.create(archive);
      scheduleManager.setSchedule(schedule);
    });

    scheduler.scheduleJob(fetchJob.getJobDetail(), fetchJob.getTrigger());
  }

  @Override
  public void stop() throws Exception {}

  @Override
  public int getPriority() {
    return FastestPathManagedPriority.LOW;
  }

//
//  public Optional<Departure> getDeparture(StationName from, StationName to, Instant departAt) {
//    departAt = ObjectUtils.defaultIfNull(departAt, Instant.now());
//    Optional<Trip> sequence = schedule.getTripForDepartureTime(from, to, departAt);
//
//    if (!sequence.isPresent()) {
//      return Optional.empty();
//    }
//
//    return Optional.of(Departure.create(sequence.get()));
//  }

}