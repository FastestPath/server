package co.fastestpath.api.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Singleton
public class FeedbackManager {
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackManager.class);

    @Inject
    public FeedbackManager() {

    }

    public void addFeedback(Feedback feedback) {
        // send it to fastestpathapp+feedback@gmail.com
        String to = "fastestpathapp+feedback@gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // change this to send feedback to ourselves from ourselves...?
                return new PasswordAuthentication(to, "<password here>");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(feedback.getEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Feedback from FastestPath");
            message.setText(feedback.getBody());
            Transport.send(message);
            LOG.info("Feedback from " + feedback.getEmail() + " sent to " + to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
