package co.fastestpath.api.gtfs;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.Duration;

import static co.fastestpath.api.FastestPathModule.FETCH_INTERVAL;

@Singleton
public class GtfsEntityFetcher {

	private static final Logger LOG = LoggerFactory.getLogger(GtfsEntityFetcher.class);

	private final Duration fetchInterval;

	private final GtfsArchiveFetchJobFactory fetchJobFactory;

	private final GtfsEntityMapper entityMapper;

	private final Scheduler scheduler;

	@Inject
  public GtfsEntityFetcher(@Named(FETCH_INTERVAL) Duration fetchInterval, GtfsArchiveFetchJobFactory fetchJobFactory,
      GtfsEntityMapper entityMapper, Scheduler scheduler) {
	  this.fetchInterval = fetchInterval;
    this.fetchJobFactory = fetchJobFactory;
    this.entityMapper = entityMapper;
    this.scheduler = scheduler;
  }

  public void fetch(GtfsEntityFetchCallback callback) {
    GtfsArchiveFetchJob fetchJob = fetchJobFactory.createJob((int) fetchInterval.toHours(), (archive) -> {
      GtfsEntityMap entities = entityMapper.map(archive.getFiles());
      callback.onFetch(entities);
    });

    try {
      scheduler.scheduleJob(fetchJob.getJobDetail(), fetchJob.getTrigger());
    } catch (SchedulerException e) {
      LOG.error("Failed to fetch GTFS entities.", e);
    }
  }
}