package co.fastestpath.api.gtfs;

import co.fastestpath.api.schedule.ScheduleManager;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class GtfsArchiveFetchJob implements Job {

  public static final String JOB_NAME = "schedule-fetch-job";

  public static final String GROUP_NAME = "fetch-job";

  private static final Logger LOG = LoggerFactory.getLogger(GtfsArchiveFetchJob.class);

  private final GtfsArchiveFetcher archiveFetcher;

  private final ScheduleManager scheduleManager;

  @Inject
  public GtfsArchiveFetchJob(GtfsArchiveFetcher archiveFetcher, ScheduleManager scheduleManager) {
    this.archiveFetcher = archiveFetcher;
    this.scheduleManager = scheduleManager;
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    LOG.info("Archive fetch job triggered.");

    if (GtfsArchiveDateChecker.isUpToDate()) {
      LOG.info("Archive is already update to date.");
      return;
    }

    GtfsArchive archive;
    try {
      archive = archiveFetcher.fetch();
    } catch (GtfsArchiveFetchingException e) {
      LOG.error("Failed to fetch archive.", e);
      return;
    }

    LOG.info("Successfully fetched archive.");
    scheduleManager.onFetch(archive);
  }
}
