package co.fastestpath.api.bootstrap

import co.fastestpath.api.bootstrap.schedule.ScheduleModule
import co.fastestpath.api.pathfinder.PathFinderManager
import com.google.inject.Injector
import org.jsoup.nodes.Entities
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Bootstrapper @Inject constructor(
  private val injector: Injector,
  private val pathFinderManager: PathFinderManager,
  private val taskScheduler: TaskScheduler
) {

  private val scheduleModule = ScheduleModule()
  private val scheduleInjector = injector.createChildInjector(scheduleModule)

  fun run() {

    // setup scheduler

  }

  fun onUpdate(entities: Entities) {
    TODO("Schedule factory to create schedule")
//    scheduleModule.update(entities)
//
//    val pathFinder = injector.getInstance(PathFinder::class.java)
//    pathFinderManager.updatePathFinder(pathFinder)
  }

}