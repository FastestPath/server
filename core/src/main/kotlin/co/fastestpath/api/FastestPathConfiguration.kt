package co.fastestpath.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.NotNull

class FastestPathConfiguration(
  @NotEmpty
  @JsonProperty("environment")
  val environment: String,

  @NotNull
  @JsonProperty("gtfs")
  val gtfsConfiguration: GtfsConfiguration

) : Configuration()
