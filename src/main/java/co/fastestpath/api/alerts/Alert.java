package co.fastestpath.api.alerts;

import java.time.Instant;

class Alert {

  private String id;

  private Instant createdOn;

  private String body;

  public Alert(String body) {
    this.createdOn = Instant.now();
    this.body = body;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public String getBody() {
    return body;
  }
}
