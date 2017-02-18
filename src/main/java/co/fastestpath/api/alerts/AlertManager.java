package co.fastestpath.api.alerts;

import org.apache.commons.lang3.NotImplementedException;
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

  private final AlertStore alertStore;

  @Inject
  public AlertManager(AlertStore alertStore) {
    this.alertStore = alertStore;
  }


  public List<Alert> getAlerts() {
    throw new NotImplementedException("Get alerts not yet implemented.");
  }

  public void addAlert(Alert alert) {
    alertStore.saveAlert(alert);
  }
}
