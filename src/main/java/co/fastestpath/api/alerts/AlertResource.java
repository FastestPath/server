package co.fastestpath.api.alerts;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
  public Response newAlert(@FormDataParam("to") String to,
      @FormDataParam("from") String from,
      @FormDataParam("html") String html,
      @FormDataParam("text") String text,
      @FormDataParam("subject") String subject) {

    AlertEmail email = new AlertEmail();
    email.setFrom(from);
    email.setTo(to);
    email.setSubject(subject);
    email.setText(text);

    LOG.info("Received new alert email. from={}, to={}, subject={}, text={},", from, to, subject, text);
    if (AlertEmailValidator.isValid(email)) {
      Alert alert = AlertFactory.from(email);
      alertManager.addAlert(alert);
      LOG.info("Added alert email.");
    } else {
      LOG.info("Alert email is not valid, discarding.");
    }
    return Response.ok().build();
  }
}
