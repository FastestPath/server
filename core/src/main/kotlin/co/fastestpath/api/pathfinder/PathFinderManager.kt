package co.fastestpath.api.pathfinder

import javax.inject.Singleton

/**
 * Path Finder Manager
 *
 * This should be injected into api resources to get schedule information.
 */

@Singleton
class PathFinderManager {

  private var pathFinder: PathFinder? = null

  fun updatePathFinder(pathFinder: PathFinder) {
    this.pathFinder = pathFinder
  }

}

