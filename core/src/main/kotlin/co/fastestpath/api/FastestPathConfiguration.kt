package co.fastestpath.api

import co.fastestpath.api.utils.firebase.FirebaseConfiguration
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class FastestPathConfiguration(

  @NotEmpty
  @JsonProperty("environment")
  val environment: String,

  @NotEmpty
  @JsonProperty("archiveUrl")
  val archiveUrl: String,

  @NotEmpty
  @JsonProperty("fetchIntervalHours")
  val fetchIntervalHours: Integer,

  @NotEmpty
  @JsonProperty("resourceDirectory")
  val resourceDirectory: String,

  @NotEmpty
  @JsonProperty("firebase")
  val firebase: FirebaseConfiguration

) : Configuration()