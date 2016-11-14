package co.fastestpath.api.alerts;

import co.fastestpath.api.scheduler.Environment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class AlertEmailValidator {

  private static final String DEV_SENDER = "john.pucciarelli@gmail.com";

  private static final String SENDER = "alerts@paalerts.com";

  private static final String SUBJECT = "PATHAlert";

  private final Environment environment;

  @Inject
  public AlertEmailValidator(Environment environment) {
    this.environment = environment;
  }

  public boolean isValid(AlertEmail alertEmail) {
    String sender = alertEmail.getFrom();
    String subject = alertEmail.getSubject();
    String body = alertEmail.getHtml();

    // allow emails to be tested in development
    if (environment == Environment.DEVELOPMENT && sender.equals(DEV_SENDER)) {
      return true;
    }

    return sender.equals(SENDER) && subject.equals(SUBJECT) && !body.isEmpty();
  }
}
