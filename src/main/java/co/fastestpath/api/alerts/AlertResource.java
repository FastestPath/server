package co.fastestpath.api.alerts;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
public class AlertResource {

  private static final Logger LOG = LoggerFactory.getLogger(AlertResource.class);

  private final AlertManager alertManager;

  @Inject
  public AlertResource(AlertManager alertManager) {
    this.alertManager = alertManager;
  }

  // TODO: look into using @BeanParam here
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
    } else {
      LOG.info("Alert email is not valid, discarding.");
    }
    return Response.ok().build();
  }
}
