package co.fastestpath.api;

import co.fastestpath.api.utils.firebase.FirebaseConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

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
