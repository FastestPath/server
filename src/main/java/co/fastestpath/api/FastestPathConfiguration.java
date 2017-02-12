package co.fastestpath.api;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

class FastestPathConfiguration extends Configuration {

  @NotEmpty
  private String environment;

  @NotNull
  private Integer fetchIntervalHours;

  @NotEmpty
  private String resourceDirectory;

  public String getEnvironment() {
    return environment;
  }

  public int getFetchIntervalHours() {
    return fetchIntervalHours;
  }

  public String getResourceDirectory() {
    return resourceDirectory;
  }
}
