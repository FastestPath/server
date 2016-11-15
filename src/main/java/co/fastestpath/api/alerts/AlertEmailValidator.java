package co.fastestpath.api.alerts;

class AlertEmailValidator {

  private static final String DEV_SENDER = "John Pucciarelli <john.pucciarelli@gmail.com>";

  private static final String SENDER = "PAalerts <alerts@paalerts.com>";

  private static final String SUBJECT = "PATHAlert";

  public static boolean isValid(AlertEmail alertEmail) {
    String sender = alertEmail.getFrom();
    String subject = alertEmail.getSubject();
    String body = alertEmail.getText();

    // allow emails to be tested in development
    if (sender.equals(DEV_SENDER)) {
      return true;
    }

    return sender.equals(SENDER) && subject.equals(SUBJECT) && !body.isEmpty();
  }

  private AlertEmailValidator() {}
}
