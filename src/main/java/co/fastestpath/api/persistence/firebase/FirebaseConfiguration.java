package co.fastestpath.api.persistence.firebase;

public class FirebaseConfiguration {

  private final String keyPath;

  private final String databaseUrl;

  public FirebaseConfiguration(String keyPath, String databaseUrl) {
    this.keyPath = keyPath;
    this.databaseUrl = databaseUrl;
  }

  public String getKeyPath() {
    return keyPath;
  }

  public String getDatabaseUrl() {
    return databaseUrl;
  }
}
