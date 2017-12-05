package co.fastestpath.api

import com.hubspot.dropwizard.guice.GuiceBundle
import io.dropwizard.testing.junit.DropwizardAppRule
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import org.testng.Assert.assertNotNull
import org.testng.Assert.fail
import org.testng.annotations.Test
import java.net.URL
import java.nio.file.Path
import java.time.Duration
import javax.inject.Inject

@Test
class FastestPathModuleTest {

  init {
    DropwizardAppRule(
        TestApplication::class.java,
        resourceFilePath("test-configuration.yml")
    )
  }
}

class TestApplication : Application<FastestPathConfiguration>() {

  private var bundle: GuiceBundle<FastestPathConfiguration>? = null

  override fun initialize(bootstrap: Bootstrap<FastestPathConfiguration>) {
    bundle = GuiceBundle.newBuilder<FastestPathConfiguration>()
        .addModule(FastestPathModule())
        .enableAutoConfig(this.javaClass.`package`.name)
        .build()

    bootstrap.addBundle(bundle)
  }

  @Throws(Exception::class)
  override fun run(configuration: FastestPathConfiguration, environment: Environment) {
    val testClass = bundle?.injector?.getInstance(TestClass::class.java)

    if (testClass == null) {
      fail("Failed to create instance of TestClass.")
      return
    }

    assertNotNull(testClass.environment)
    assertNotNull(testClass.archiveUrl)
    assertNotNull(testClass.fetchInterval)
    assertNotNull(testClass.resourceDir)
  }
}

class TestClass @Inject constructor(
    val environment: Environment,
    val archiveUrl: URL,
    val fetchInterval: Duration,
    val resourceDir: Path
)
