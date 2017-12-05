package co.fastestpath.api

import co.fastestpath.api.bootstrap.BootstrapModule
import co.fastestpath.api.bootstrap.Bootstrapper
import co.fastestpath.api.utils.serialization.configureMapper
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.module.SimpleModule
import com.hubspot.dropwizard.guice.GuiceBundle
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

open class FastestPathApplication : Application<FastestPathConfiguration>() {

  companion object {
    private val MODULE_VERSION = Version(1, 0, 0, null, null, null)
    private val MODULE = SimpleModule("FastestPathModule", MODULE_VERSION)
  }

  private var bundle : GuiceBundle<FastestPathConfiguration>? = null

  override fun initialize(bootstrap: Bootstrap<FastestPathConfiguration>) {
    bundle = GuiceBundle.newBuilder<FastestPathConfiguration>()
      .setConfigClass(FastestPathConfiguration::class.java)
      .addModule(FastestPathModule())
      .addModule(BootstrapModule())
      .enableAutoConfig(this.javaClass.`package`.name)
      .build()

    bootstrap.addBundle(bundle)
  }

  override fun run(configuration: FastestPathConfiguration, environment: Environment) {
    configureMapper(environment.objectMapper, MODULE)
    val bootstrapper = bundle?.injector?.getInstance(Bootstrapper::class.java)
    bootstrapper?.run()
  }
}
