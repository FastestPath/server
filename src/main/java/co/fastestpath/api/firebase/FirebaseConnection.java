package co.fastestpath.api.firebase;

import co.fastestpath.api.Environment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredential;
import com.google.firebase.auth.FirebaseCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static co.fastestpath.api.Environment.PRODUCTION;

public class FirebaseConnection {

  private static final Logger LOG = LoggerFactory.getLogger(FirebaseConnection.class);

  private static FirebaseConnection connection;

  public static FirebaseConnection connect(FirebaseConfiguration configuration, Environment environment) {
    if (connection == null) {
      connection = new FirebaseConnection(configuration, environment);
    }
    return connection;
  }

  private FirebaseConnection(FirebaseConfiguration configuration, Environment environment) {
    Map<String, Object> auth = new HashMap<>();
    auth.put("uid", configuration.uid);

    FirebaseCredential credential;
    try {
      credential = getCredentials(configuration, environment);
    } catch (FileNotFoundException e) {
      LOG.error("Unable to open Firebase key.", e);
      return;
    }

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredential(credential)
        .setDatabaseAuthVariableOverride(auth)
        .setDatabaseUrl(configuration.databaseUrl)
        .build();

    LOG.info("Initializing Firebase...");
    FirebaseApp.initializeApp(options);
  }

  private static FirebaseCredential getCredentials(FirebaseConfiguration configuration, Environment environment)
      throws FileNotFoundException {
    if (environment == PRODUCTION) {
      LOG.info("Using production credentials.");
      return FirebaseCredentials.applicationDefault();
    }

    LOG.info("Using development credentials.");
    FileInputStream serviceAccount = new FileInputStream(configuration.keyPath);
    return FirebaseCredentials.fromCertificate(serviceAccount);
  }
}
