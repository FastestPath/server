package co.fastestpath.api.schedule;

import co.fastestpath.api.managed.FastestPathManaged;
import co.fastestpath.api.managed.FastestPathManagedPriority;

import javax.inject.Singleton;
import java.time.Instant;

@Singleton
public class ScheduleManager implements FastestPathManaged {

  private Schedule schedule;

  private Instant modifiedOn;

  @Override
  public void start() throws Exception {

  }

  @Override
  public void stop() throws Exception {

  }

  @Override
  public int getPriority() {
    return FastestPathManagedPriority.LOW;
  }
}
