package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsEntityFetcher;
import co.fastestpath.api.managed.FastestPathManaged;
import co.fastestpath.api.managed.FastestPathManagedPriority;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScheduleManager implements FastestPathManaged {

  private final GtfsEntityFetcher entityFetcher;

  private Schedule schedule;

  @Inject
  public ScheduleManager(GtfsEntityFetcher entityFetcher) {
    this.entityFetcher = entityFetcher;
  }

  @Override
  public void start() throws Exception {
    entityFetcher.fetch((entities) -> schedule = ScheduleFactory.create(entities));
  }

  @Override
  public void stop() throws Exception {}

  @Override
  public int getPriority() {
    return FastestPathManagedPriority.LOW;
  }
}
