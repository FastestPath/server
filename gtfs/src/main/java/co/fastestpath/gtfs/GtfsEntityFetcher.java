package co.fastestpath.api.gtfs;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.Instant;

import static co.fastestpath.api.FastestPathModule.FETCH_INTERVAL;

@Singleton
public class GtfsEntityFetcher {

	private static final Logger LOG = LoggerFactory.getLogger(GtfsEntityFetcher.class);

	private final Duration fetchInterval;

	private final Scheduler scheduler;

	@Inject
  public GtfsEntityFetcher(@Named(FETCH_INTERVAL) Duration fetchInterval, Scheduler scheduler) {
	  this.fetchInterval = fetchInterval;
    this.scheduler = scheduler;
  }

  public void fetch() {
    JobDetail detail = GtfsArchiveFetchJobFactory.createDetail();
    Trigger trigger = GtfsArchiveFetchJobFactory.createTrigger((int) fetchInterval.toHours(), Instant.now());

    try {
      scheduler.scheduleJob(detail, trigger);
    } catch (SchedulerException e) {
      LOG.error("Failed to fetch GTFS entities.", e);
    }
  }
}