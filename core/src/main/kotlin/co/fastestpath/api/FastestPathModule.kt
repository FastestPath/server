package co.fastestpath.api

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
  }

  override fun configure() {}

  @Provides
  @Named(ENVIRONMENT)
  fun getEnvironment(configuration: FastestPathConfiguration): Environment = environmentOf(configuration.environment)

  @Provides
  fun getGtfsConfiguration(configuration: FastestPathConfiguration): GtfsConfiguration = configuration.gtfsConfiguration
}
