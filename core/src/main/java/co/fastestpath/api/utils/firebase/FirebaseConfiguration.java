package co.fastestpath.api.utils.firebase;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class FirebaseConfiguration extends Configuration {

  @JsonProperty("keyPath")
  public String keyPath;

  @NotEmpty
  @JsonProperty("databaseUrl")
  public String databaseUrl;

  @NotEmpty
  @JsonProperty("uid")
  public String uid;
}
