package co.fastestpath.api.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Singleton
class AlertManager {

  private static final Logger LOG = LoggerFactory.getLogger(AlertManager.class);

  // TODO: remove old alerts
  private static final Instant THIRTY_DAYS = Instant.now()
      .minus(30, ChronoUnit.DAYS);

  private final AlertStore alertStore;

  @Inject
  public AlertManager(AlertStore alertStore) {
    this.alertStore = alertStore;
  }

  public void addAlert(Alert alert) {
    alertStore.saveAlert(alert);
  }
}
