package co.fastestpath.api

import co.fastestpath.api.utils.Environment
import com.google.inject.AbstractModule
import com.google.inject.Provides
import java.net.URL
import java.nio.file.Path

import javax.inject.Named
import java.nio.file.Paths
import java.time.Duration

class FastestPathModule : AbstractModule() {

  companion object {
    const val ENVIRONMENT = "environment"
    const val RESOURCES_DIR = "resources"
    const val ARCHIVE_URL = "archive-url"
    const val FETCH_INTERVAL_HOURS = "fetch-interval-hours"
  }

  override fun configure() {
  }

  @Provides
  @Named(ENVIRONMENT)
  fun getEnvironment(configuration: FastestPathConfiguration): Environment =
      when (configuration.environment) {
        "production" -> Environment.PRODUCTION
        "test" -> Environment.TEST
        else -> Environment.DEVELOPMENT
      }

  @Provides
  @Named(FETCH_INTERVAL_HOURS)
  fun getFetchInterval(configuration: FastestPathConfiguration): Duration =
      Duration.ofHours(configuration.fetchIntervalHours.toLong())

  @Provides
  @Named(RESOURCES_DIR)
  fun getResourcesDirectory(configuration: FastestPathConfiguration): Path =
      Paths.get(configuration.resourceDirectory)

  @Provides
  @Named(ARCHIVE_URL)
  fun getArchiveUrl(configuration: FastestPathConfiguration): URL =
      URL(configuration.archiveUrl)
}
