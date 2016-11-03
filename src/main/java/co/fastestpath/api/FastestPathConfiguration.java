package co.fastestpath.api;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

class FastestPathConfiguration extends Configuration {

  @NotEmpty
  private String environment;

  private int fetchIntervalHours;

  public String getEnvironment() {
    return environment;
  }

  public int getFetchIntervalHours() {
    return fetchIntervalHours;
  }
}
