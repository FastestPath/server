package co.fastestpath.api.bootstrap

import co.fastestpath.api.GtfsConfiguration
import co.fastestpath.api.bootstrap.archive.Archive
import co.fastestpath.api.bootstrap.archive.fetch.ArchiveFetcher
import co.fastestpath.api.bootstrap.schedule.ScheduleFactory
import co.fastestpath.api.bootstrap.schedule.ScheduleModule
import co.fastestpath.api.pathfinder.PathFinder
import co.fastestpath.api.pathfinder.PathFinderManager
import co.fastestpath.utils.scheduler.TaskScheduler
import com.google.inject.Injector
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Sets up the schedule fetch task required to initialize
 * the path finder.
 */
@Singleton
class Bootstrapper @Inject constructor(
  private val archiveFetcher: ArchiveFetcher,
  private val scheduleFactory: ScheduleFactory,
  private val scheduleModule: ScheduleModule,
  private val gtfsConfiguration: GtfsConfiguration,
  private val pathFinderManager: PathFinderManager,
  private val taskScheduler: TaskScheduler,
  injector: Injector
) {

  private val scheduleInjector = injector.createChildInjector(scheduleModule)

  fun run() {
    val archiveUrl = gtfsConfiguration.archiveUrl
    val savePath = gtfsConfiguration.savePath
    val fetchInterval = gtfsConfiguration.fetchInterval

    taskScheduler.scheduleAtRate(Runnable {
      val archive = archiveFetcher.fetch(archiveUrl, savePath)
      update(archive)
    }, fetchInterval)
  }

  private fun update(archive: Archive) {
    val schedule = scheduleFactory.create(archive)
    scheduleModule.update(schedule)

    val pathFinder = scheduleInjector.getInstance(PathFinder::class.java)
    pathFinderManager.updatePathFinder(pathFinder)
  }
}
