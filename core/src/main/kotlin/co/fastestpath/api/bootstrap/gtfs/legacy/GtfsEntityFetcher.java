package co.fastestpath.api.bootstrap.gtfs.legacy;

import co.fastestpath.api.FetchArchiveJobFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.Instant;

@Singleton
public class GtfsEntityFetcher {

	private static final Logger LOG = LoggerFactory.getLogger(GtfsEntityFetcher.class);

	private final Scheduler scheduler;

	@Inject
  public GtfsEntityFetcher(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  public void fetch(Duration fetchInterval) {
    JobDetail detail = FetchArchiveJobFactory.createDetail();
    Trigger trigger = FetchArchiveJobFactory.createTrigger((int) fetchInterval.toHours(), Instant.now());

    try {
      scheduler.scheduleJob(detail, trigger);
    } catch (SchedulerException e) {
      LOG.error("Failed to fetch GTFS entities.", e);
    }
  }
}