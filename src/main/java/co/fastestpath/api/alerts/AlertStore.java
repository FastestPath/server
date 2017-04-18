package co.fastestpath.api.alerts;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AlertStore {

  public final String ALERTS_REF = "/alerts";

  private static final Logger LOG = LoggerFactory.getLogger(AlertStore.class);

  private final DatabaseReference databaseReference;

  @Inject
  public AlertStore(FirebaseDatabase firebaseDatabase) {
    databaseReference = firebaseDatabase.getReference(ALERTS_REF);
  }

  public void saveAlert(Alert alert) {
    databaseReference.push()
        .setValue(alert, (databaseError, databaseReference) -> LOG.info("Saved new alert."));
  }
}
