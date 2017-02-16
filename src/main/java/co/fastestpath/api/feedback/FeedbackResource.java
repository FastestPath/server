package co.fastestpath.api.feedback;

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

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    private static final Logger LOG = LoggerFactory.getLogger(FeedbackResource.class);

    private final FeedbackManager feedbackManager;

    @Inject
    public FeedbackResource(FeedbackManager feedbackManager) {
        this.feedbackManager = feedbackManager;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response newAlert(@FormDataParam("email") String email, @FormDataParam("body") String body) {
        Feedback feedback = new Feedback(email, body);
        feedbackManager.addFeedback(feedback);
        LOG.info("Added feedback.");
        return Response.ok().build();
    }

}
