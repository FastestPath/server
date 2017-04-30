package co.fastestpath.api.gtfs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GtfsArchiveFetchJob implements Job {

  public static final String JOB_NAME = "schedule-fetch-job";

  public static final String GROUP_NAME = "fetch-job";

  private static final Logger LOG = LoggerFactory.getLogger(GtfsArchiveFetchJob.class);

  private final GtfsArchiveFetcher archiveFetcher;

  private final GtfsArchiveFetchCallback callback;

  private final JobDetail jobDetail;

  private final Trigger trigger;

  private GtfsArchiveFetchJob(Builder builder) {
    archiveFetcher = builder.archiveFetcher;
    callback = builder.callback;
    jobDetail = builder.jobDetail;
    trigger = builder.trigger;
  }

  public static Builder builder() {
    return new Builder();
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
    callback.onFetch(archive);
  }

  public JobDetail getJobDetail() {
    return jobDetail;
  }

  public Trigger getTrigger() {
    return trigger;
  }

  public static final class Builder {
    private GtfsArchiveFetcher archiveFetcher;
    private GtfsArchiveFetchCallback callback;
    private JobDetail jobDetail;
    private Trigger trigger;

    private Builder() {
    }

    public Builder archiveFetcher(GtfsArchiveFetcher archiveFetcher) {
      this.archiveFetcher = archiveFetcher;
      return this;
    }

    public Builder callback(GtfsArchiveFetchCallback callback) {
      this.callback = callback;
      return this;
    }

    public Builder jobDetail(JobDetail jobDetail) {
      this.jobDetail = jobDetail;
      return this;
    }

    public Builder trigger(Trigger trigger) {
      this.trigger = trigger;
      return this;
    }

    public GtfsArchiveFetchJob build() {
      return new GtfsArchiveFetchJob(this);
    }
  }
}
