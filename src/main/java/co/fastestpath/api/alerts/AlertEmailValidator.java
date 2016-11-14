package co.fastestpath.api.alerts;

class AlertEmailValidator {

  private static final String DEV_SENDER = "john.pucciarelli@gmail.com";

  private static final String SENDER = "alerts@paalerts.com";

  private static final String SUBJECT = "PATHAlert";

  public static boolean isValid(AlertEmail alertEmail) {
    String sender = alertEmail.getFrom();
    String subject = alertEmail.getSubject();
    String body = alertEmail.getHtml();

    // allow emails to be tested in development
    if (sender.equals(DEV_SENDER)) {
      return true;
    }

    return sender.equals(SENDER) && subject.equals(SUBJECT) && !body.isEmpty();
  }

  private AlertEmailValidator() {}
}
