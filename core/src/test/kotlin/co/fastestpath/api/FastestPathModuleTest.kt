package co.fastestpath.api

import com.hubspot.dropwizard.guice.GuiceBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import junit.framework.Assert.assertNotNull
import org.junit.ClassRule
import org.junit.Test

class TestApplication : FastestPathApplication() {

  companion object {
    @ClassRule @JvmField
    val APP_RULE: DropwizardAppRule<FastestPathConfiguration> = DropwizardAppRule(
        TestApplication::class.java,
        ResourceHelpers.resourceFilePath("test-configuration.yml")
    )
  }

  override fun initialize(bootstrap: Bootstrap<FastestPathConfiguration>) {
    bundle = GuiceBundle.newBuilder<FastestPathConfiguration>()
        .setConfigClass(FastestPathConfiguration::class.java)
        .addModule(FastestPathModule())
        .build()
  }

  override fun run(configuration: FastestPathConfiguration, environment: Environment) { }

  @Test
  fun test() {
    val configuration = APP_RULE.configuration
    assertNotNull(configuration.archiveUrl)
    assertNotNull(configuration.environment)
    assertNotNull(configuration.fetchIntervalHours)
    assertNotNull(configuration.resourceDirectory)
  }
}
