package co.fastestpath.api.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Singleton
class AlertManager {

  private static final Logger LOG = LoggerFactory.getLogger(AlertManager.class);

  private static final Instant THIRTY_DAYS = Instant.now()
      .minus(30, ChronoUnit.DAYS);

  @Inject
  public AlertManager() {

  }

  public List<Alert> getAlerts() {
    return null;
  }

  public void addAlert(Alert alert) {
    LOG.debug("Removing alerts older than 30 days");
  }
}
