package co.fastestpath.api.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class FeedbackManager {
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackManager.class);

    @Inject
    public FeedbackManager() {

    }

    public void addFeedback(Feedback feedback) {
        // send it to fastestpathapp+feedback@gmail.com
    }

    public List<Feedback> getFeedback() {
        return new ArrayList<Feedback>();
    }
}
