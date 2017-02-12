package co.fastestpath.api;

import co.fastestpath.api.schedule.ScheduleFetchJob;
import io.dropwizard.lifecycle.Managed;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.Instant;

import static co.fastestpath.api.FastestPathModule.FETCH_INTERVAL;

@Singleton
public class FastestPath implements Managed {

  private final Duration fetchInterval;

  private final Scheduler scheduler;

  @Inject
  public FastestPath(@Named(FETCH_INTERVAL) Duration fetchInterval, Scheduler scheduler) {
    this.fetchInterval = fetchInterval;
    this.scheduler = scheduler;
  }

  @Override
  public void start() throws Exception {
    JobDetail jobDetail = ScheduleFetchJob.createDetail();

    int fetchIntervalHours = (int) fetchInterval.toHours();
    Trigger trigger = ScheduleFetchJob.createTrigger(fetchIntervalHours, Instant.now());

    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }

  @Override
  public void stop() throws Exception {
    scheduler.shutdown();
  }
}
