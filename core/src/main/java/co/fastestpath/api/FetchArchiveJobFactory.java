package co.fastestpath.api;

import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import java.time.Instant;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class FetchArchiveJobFactory {

  private FetchArchiveJobFactory() {}

  public static JobDetail createDetail() {
    return newJob(GtfsArchiveFetchJob.class)
        .build();
  }

  public static Trigger createTrigger(int repeatIntervalHours, Instant startAt) {
    ScheduleBuilder<SimpleTrigger> scheduleBuilder = simpleSchedule()
        .withIntervalInHours(repeatIntervalHours)
        .repeatForever();

    return newTrigger()
        .withIdentity(GtfsArchiveFetchJob.JOB_NAME, GtfsArchiveFetchJob.GROUP_NAME)
        .startAt(Date.from(startAt))
        .withSchedule(scheduleBuilder)
        .build();
  }
}
