package co.fastestpath.api.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
public class AlertResource {

  private static final Logger LOG = LoggerFactory.getLogger(AlertResource.class);

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
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response newAlert(AlertEmail email) {
    LOG.info("Received new alert email.");
    if (AlertEmailValidator.isValid(email)) {
      Alert alert = AlertFactory.from(email);
      alertManager.addAlert(alert);
      LOG.info("Added alert email.");
      return Response.ok().build();
    }

    LOG.info("Alert email is not valid, discarding.");
    return Response.status(Status.BAD_REQUEST).build();
  }
}
