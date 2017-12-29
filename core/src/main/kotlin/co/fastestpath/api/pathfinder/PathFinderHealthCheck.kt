package co.fastestpath.api.pathfinder

import com.google.inject.Singleton
import com.hubspot.dropwizard.guice.InjectableHealthCheck

@Singleton
class PathFinderHealthCheck : InjectableHealthCheck() {

  @Throws(Exception::class)
  override fun check(): Result {
    return Result.healthy()
  }

  override fun getName(): String {
    return "PathFinderHealthCheck"
  }
}
