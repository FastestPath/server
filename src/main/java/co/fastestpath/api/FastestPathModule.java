package co.fastestpath.api;

import co.fastestpath.api.scheduler.Environment;
import co.fastestpath.api.scheduler.SchedulerProvider;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser.Feature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static co.fastestpath.api.scheduler.Environment.PRODUCTION;

public class FastestPathModule extends AbstractModule {

  public static final String ENVIRONMENT = "environment";

  public static final String RESOURCES = "resources";

  public static final String FETCH_INTERVAL = "fetch-interval";

  private final CsvMapper CSV_MAPPER = new CsvMapper();

  @Provides
  @Named(ENVIRONMENT)
  public Environment getEnvironment(FastestPathConfiguration configuration) {
    return configuration.getEnvironment().equals("production")
        ? PRODUCTION : Environment.DEVELOPMENT;
  }

  @Provides
  @Named(FETCH_INTERVAL)
  public Duration getFetchInterval(FastestPathConfiguration configuration) {
    return Duration.ofHours(configuration.getFetchIntervalHours());
  }

  @Provides
  @Named(RESOURCES)
  public Path getResourceDirectory(FastestPathConfiguration configuration) {
    return getEnvironment(configuration) == PRODUCTION
        ? Paths.get("/tmp/data")
        : Paths.get("src/main/resources");
  }

  @Override
  protected void configure() {
    bind(SchedulerFactory.class)
        .to(StdSchedulerFactory.class)
        .in(Singleton.class);

    bind(Scheduler.class)
        .toProvider(SchedulerProvider.class)
        .in(Singleton.class);

    CSV_MAPPER.registerModule(new JavaTimeModule());
    CSV_MAPPER.enable(Feature.WRAP_AS_ARRAY);
    bind(CsvMapper.class).toInstance(CSV_MAPPER);
  }
}
