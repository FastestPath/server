package co.fastestpath.api;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

class FastestPathConfiguration extends Configuration {

  @NotEmpty
  private String environment;

  @NotEmpty
  private String resourceDirectory;

  public String getEnvironment() {
    return environment;
  }

  public String getResourceDirectory() {
    return resourceDirectory;
  }
}
