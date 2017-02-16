package co.fastestpath.api.persistence.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static co.fastestpath.api.FastestPathModule.FIREBASE_CONFIG;

public class FirebaseManager implements Managed {

  private static final Logger LOG = LoggerFactory.getLogger(FirebaseManager.class);

  private final FirebaseConfiguration configuration;

  @Inject
  public FirebaseManager(@Named(FIREBASE_CONFIG) FirebaseConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public void start() throws Exception {
    FileInputStream serviceAccount;
    try {
      serviceAccount = new FileInputStream(configuration.getKeyPath());
    } catch (FileNotFoundException e) {
      LOG.error("Unable to open Firebase key.", e);
      return;
    }

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
        .setDatabaseUrl(configuration.getDatabaseUrl())
        .build();

    LOG.info("Initializing Firebase...");
    FirebaseApp.initializeApp(options);
  }

  @Override
  public void stop() throws Exception {
  }
}
