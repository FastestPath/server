package co.fastestpath.api.bootstrap

import co.fastestpath.api.FastestPathModule
import co.fastestpath.api.bootstrap.gtfs.archive.fetch.ArchiveFetcher
import co.fastestpath.api.bootstrap.schedule.ScheduleModule
import co.fastestpath.api.pathfinder.PathFinderManager
import co.fastestpath.scheduler.TaskScheduler
import com.google.inject.Injector
import org.jsoup.nodes.Entities
import java.net.URL
import java.nio.file.Path
import java.time.Duration
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Sets up the schedule fetch task required to initialize
 * the path finder.
 */
@Singleton
class Bootstrapper @Inject constructor(
  @Named(FastestPathModule.RESOURCES_DIR) private val resourcesDirectory: Path,
  @Named(FastestPathModule.FETCH_INTERVAL_HOURS) private val fetchInterval: Duration,
  @Named(FastestPathModule.ARCHIVE_URL) private val archiveUrl: URL,
  private val archiveFetcher: ArchiveFetcher,
  private val scheduleModule: ScheduleModule,
  private val injector: Injector,
  private val pathFinderManager: PathFinderManager,
  private val taskScheduler: TaskScheduler
) {

  private val scheduleInjector = injector.createChildInjector(scheduleModule)

  fun run() {
    taskScheduler.scheduleAtRate(Runnable {
      archiveFetcher.fetch(archiveUrl, resourcesDirectory)
    }, fetchInterval)
  }

  fun onUpdate(entities: Entities) {
    TODO("Schedule factory to create schedule")
//    scheduleModule.update(entities)
//
//    val pathFinder = injector.getInstance(PathFinder::class.java)
//    pathFinderManager.updatePathFinder(pathFinder)
  }

  fun run(block: Int) {
  }
}
