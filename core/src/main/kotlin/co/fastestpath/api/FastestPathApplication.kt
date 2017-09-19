package co.fastestpath.api;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static co.fastestpath.api.utils.SerializationSettings.SerializationSettingsKt.configureMapper;

public class FastestPathApplication extends Application<FastestPathConfiguration> {

  private static final Version MODULE_VERSION = new Version(1, 0, 0, null, null, null);
  private static final SimpleModule MODULE = new SimpleModule("FastestPathModule", MODULE_VERSION);

  public static void main(String[] args) throws Exception {
    new FastestPathApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<FastestPathConfiguration> bootstrap) {
    GuiceBundle<FastestPathConfiguration> guiceBundle = GuiceBundle.<FastestPathConfiguration>newBuilder()
        .setConfigClass(FastestPathConfiguration.class)
        .addModule(new FastestPathModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .build();

//    bootstrap.addBundle(new MultiPartBundle());
    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(FastestPathConfiguration configuration, Environment environment) throws Exception {
    ObjectMapper mapper = environment.getObjectMapper();

    configureMapper(mapper);

    mapper.registerModule(MODULE);
  }
}
