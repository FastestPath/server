package co.fastestpath.api.alerts;

class AlertFactory {

  public static Alert from(AlertEmail email) {
    return new Alert(email.getText());
  }

  private AlertFactory() {}
}
