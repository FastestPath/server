package co.fastestpath.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.annotations.VisibleForTesting;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

@VisibleForTesting
public class FastestPathApplication extends Application<FastestPathConfiguration> {

  public static void main(String[] args) throws Exception {
    new FastestPathApplication().run(args);
  }

  public GuiceBundle<FastestPathConfiguration> guiceBundle;

  @Override
  public void initialize(Bootstrap<FastestPathConfiguration> bootstrap) {
    guiceBundle = GuiceBundle.<FastestPathConfiguration>newBuilder()
        .setConfigClass(FastestPathConfiguration.class)
        .addModule(new FastestPathModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .build();

    bootstrap.addBundle(new MultiPartBundle());
    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(FastestPathConfiguration configuration, Environment environment) throws Exception {
    ObjectMapper mapper = environment.getObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    SimpleModule module = new SimpleModule("FastestPathModule", new Version(1, 0, 0, null, null, null));
    mapper.registerModule(module);

    mapper.registerModule(new JavaTimeModule());
  }
}
