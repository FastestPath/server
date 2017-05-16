package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsArchive;
import co.fastestpath.api.gtfs.GtfsEntityFetcher;
import co.fastestpath.api.gtfs.GtfsEntityMap;
import co.fastestpath.api.gtfs.GtfsEntityMapper;
import co.fastestpath.api.managed.FastestPathManaged;
import co.fastestpath.api.managed.FastestPathManagedPriority;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScheduleManager implements FastestPathManaged {

  private final GtfsEntityFetcher entityFetcher;

  private final GtfsEntityMapper entityMapper;

  private Schedule schedule;

  @Inject
  public ScheduleManager(GtfsEntityFetcher entityFetcher, GtfsEntityMapper entityMapper) {
    this.entityFetcher = entityFetcher;
    this.entityMapper = entityMapper;
  }

  @Override
  public void start() throws Exception {
    entityFetcher.fetch();
  }

  @Override
  public void stop() throws Exception {}

  @Override
  public int getPriority() {
    return FastestPathManagedPriority.LOW;
  }

  public void onFetch(GtfsArchive archive) {
    GtfsEntityMap entities = entityMapper.map(archive.getFiles());
    this.schedule = ScheduleFactory.create(entities);
    int a = 5;
  }
}
