package co.fastestpath.api.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Stack;

@Singleton
class AlertManager {

  private static final Logger LOG = LoggerFactory.getLogger(AlertManager.class);

  private static final int MAX_ALERTS = 10;

  private final Stack<Alert> alerts;

  @Inject
  public AlertManager() {
    this.alerts = new Stack<>();
  }

  public List<Alert> getAlerts() {
    return alerts;
  }

  public void addAlert(Alert alert) {
    alerts.add(alert);
    // remove old alerts
    while (alerts.size() > MAX_ALERTS) {
      alerts.pop();
    }
  }
}
