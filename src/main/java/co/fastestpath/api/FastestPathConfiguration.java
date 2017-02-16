package co.fastestpath.api;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

class FastestPathConfiguration extends Configuration {

  @NotEmpty
  public String environment;

  @NotNull
  public Integer fetchIntervalHours;

  @NotEmpty
  public String resourceDirectory;

  @NotEmpty
  public String firebaseKeyPath;

  @NotEmpty
  public String firebaseDatabaseUrl;

}
