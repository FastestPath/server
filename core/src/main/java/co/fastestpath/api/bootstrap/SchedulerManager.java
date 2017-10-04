package co.fastestpath.api.bootstrap;

import co.fastestpath.api.manager.FastestPathManaged;
import co.fastestpath.api.manager.FastestPathManagedPriority;
import org.quartz.Scheduler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SchedulerManager implements FastestPathManaged {

  private final Scheduler scheduler;

  @Inject
  public SchedulerManager(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public void start() throws Exception {
    scheduler.start();
  }

  @Override
  public void stop() throws Exception {
    scheduler.shutdown(false);
  }

  @Override
  public int getPriority() {
    return FastestPathManagedPriority.HIGH;
  }
}