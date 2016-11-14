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

class FastestPathModule extends AbstractModule {

  private final CsvMapper CSV_MAPPER = new CsvMapper();

  @Provides
  @Named("resources")
  public Path getResourceDirectory(FastestPathConfiguration configuration) {
    return configuration.getEnvironment().equals("production")
        ? Paths.get("/tmp/data") : Paths.get("src/main/resources");
  }

  @Provides
  public Environment getEnvironment(FastestPathConfiguration configuration) {
    return configuration.getEnvironment().equals("production")
        ? Environment.PRODUCTION : Environment.DEVELOPMENT;
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
