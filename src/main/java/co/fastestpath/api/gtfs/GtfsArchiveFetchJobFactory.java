package co.fastestpath.api.gtfs;

import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Singleton
public class GtfsArchiveFetchJobFactory {

  private final GtfsArchiveFetcher archiveFetcher;

  @Inject
  public GtfsArchiveFetchJobFactory(GtfsArchiveFetcher archiveFetcher) {
    this.archiveFetcher = archiveFetcher;
  }

  public GtfsArchiveFetchJob createJob(int repeatIntervalHours, GtfsArchiveFetchCallback callback) {
    return GtfsArchiveFetchJob.builder()
        .archiveFetcher(archiveFetcher)
        .callback(callback)
        .jobDetail(createDetail())
        .trigger(createTrigger(repeatIntervalHours, Instant.now()))
        .build();
  }

  public GtfsArchiveFetchJob createJob(int repeatIntervalHours, Instant startAt, GtfsArchiveFetchCallback callback) {
    return GtfsArchiveFetchJob.builder()
        .archiveFetcher(archiveFetcher)
        .callback(callback)
        .jobDetail(createDetail())
        .trigger(createTrigger(repeatIntervalHours, startAt))
        .build();
  }

  private static JobDetail createDetail() {
    return newJob(GtfsArchiveFetchJob.class)
        .build();
  }

  private static Trigger createTrigger(int repeatIntervalHours, Instant startAt) {
    Date start = Date.from(startAt);

    ScheduleBuilder<SimpleTrigger> scheduleBuilder = simpleSchedule()
        .withIntervalInHours(repeatIntervalHours)
        .repeatForever();

    return newTrigger()
        .withIdentity(GtfsArchiveFetchJob.JOB_NAME, GtfsArchiveFetchJob.GROUP_NAME)
        .startAt(start)
        .withSchedule(scheduleBuilder)
        .build();
  }
}
