package co.fastestpath.api.alerts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

@Document(collection = "alerts")
class Alert {

  @Id
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
