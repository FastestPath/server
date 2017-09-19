package co.fastestpath.api.utils.firebase;

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

public class FirebaseConnection {

  private static final Logger LOG = LoggerFactory.getLogger(FirebaseConnection.class);

  private static FirebaseConnection connection;

  public static FirebaseConnection connect(FirebaseConfiguration configuration) {
    if (connection == null) {
      connection = new FirebaseConnection(configuration);
    }
    return connection;
  }

  private FirebaseConnection(FirebaseConfiguration configuration) {
    Map<String, Object> auth = new HashMap<>();
    auth.put("uid", configuration.uid);

    FirebaseCredential credential;
    try {
      credential = getCredentials(configuration);
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

  private static FirebaseCredential getCredentials(FirebaseConfiguration configuration)
      throws FileNotFoundException {
    FileInputStream serviceAccount = new FileInputStream(configuration.keyPath);
    return FirebaseCredentials.fromCertificate(serviceAccount);
  }
}
