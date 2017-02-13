package co.fastestpath.api.schedule;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class ScheduleFetchJob implements Job {

  private static final Logger LOG = LoggerFactory.getLogger(ScheduleFetchJob.class);

  private static final String JOB_NAME = "schedule-fetch-job";

  private static final String GROUP_NAME = "fetch-job";

  private final ScheduleManager scheduleManager;

  @Inject
  public ScheduleFetchJob(ScheduleManager scheduleManager) {
    this.scheduleManager = scheduleManager;
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    LOG.info("Schedule fetchSchedule job triggered.");
    scheduleManager.fetchLatest();
  }

  public static JobDetail createDetail() {
    return newJob(ScheduleFetchJob.class)
        .build();
  }

  public static Trigger createTrigger(int repeatIntervalHours, Instant startTime) {
    Date start = Date.from(startTime);

    ScheduleBuilder<SimpleTrigger> scheduleBuilder = simpleSchedule()
        .withIntervalInHours(repeatIntervalHours)
        .repeatForever();

    return newTrigger()
        .withIdentity(JOB_NAME, GROUP_NAME)
        .startAt(start)
        .withSchedule(scheduleBuilder)
        .build();
  }
}
