package co.fastestpath.api;

import co.fastestpath.api.firebase.FirebaseConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@VisibleForTesting
public class FastestPathConfiguration extends Configuration {

  @NotEmpty
  @JsonProperty("environment")
  String environment;

  @NotNull
  @JsonProperty("fetchIntervalHours")
  Integer fetchIntervalHours;

  @NotEmpty
  @JsonProperty("resourceDirectory")
  String resourceDirectory;

  @NotNull
  @JsonProperty("firebase")
  FirebaseConfiguration firebase;
}
