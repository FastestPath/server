package co.fastestpath.api.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    FileInputStream serviceAccount;
    try {
      serviceAccount = new FileInputStream(configuration.keyPath);
    } catch (FileNotFoundException e) {
      LOG.error("Unable to open Firebase key.", e);
      return;
    }

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
        .setDatabaseUrl(configuration.databaseUrl)
        .build();

    LOG.info("Initializing Firebase...");
    FirebaseApp.initializeApp(options);
  }
}
