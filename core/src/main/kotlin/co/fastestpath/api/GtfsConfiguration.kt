package co.fastestpath.api

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Duration

class GtfsConfiguration(
  @NotEmpty
  @JsonProperty("archiveUrl")
  archiveUrl: String, @NotEmpty
  @JsonProperty("fetchIntervalHours")
  fetchIntervalHours: Int, @NotEmpty
  @JsonProperty("saveLocation")
  saveLocation: String
) {
  val archiveUrl = URL(archiveUrl)
  val fetchInterval: Duration = Duration.ofHours(fetchIntervalHours.toLong())
  val savePath: Path = Paths.get(saveLocation)
}
