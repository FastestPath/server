package co.fastestpath.api.alerts;

import com.google.common.collect.ImmutableList;

class AlertEmailValidator {

  private static final ImmutableList<String> DEV_SENDERS = ImmutableList.of("John Pucciarelli <john.pucciarelli@gmail.com>", "Andrew Allison <andrewrobertallison@gmail.com>");

  private static final String SENDER = "\"PAalerts\" <alerts@paalerts.com>";

  private static final String SUBJECT = "PATHAlert";

  public static boolean isValid(AlertEmail alertEmail) {
    String sender = alertEmail.getFrom();
    String subject = alertEmail.getSubject();
    String body = alertEmail.getText();

    // allow emails to be tested in development
    if (DEV_SENDERS.contains(sender)) {
      return true;
    }

    return sender.equals(SENDER) && subject.equals(SUBJECT) && !body.isEmpty();
  }

  private AlertEmailValidator() {}
}
