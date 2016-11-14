package co.fastestpath.api.alerts;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
public class AlertResource {

  private final AlertManager alertManager;

  @Inject
  public AlertResource(AlertManager alertManager) {
    this.alertManager = alertManager;
  }

  @GET
  public List<Alert> getAlerts() {
    return alertManager.getAlerts();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void newAlert(AlertEmail email) {
    if (AlertEmailValidator.isValid(email)) {
      Alert alert = AlertFactory.from(email);
      alertManager.addAlert(alert);
    }
  }
}
