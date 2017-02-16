package co.fastestpath.api.alerts;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static co.fastestpath.api.FastestPathModule.DATABASE_ROOT;

@Singleton
public class AlertStore {

  private static final Logger LOG = LoggerFactory.getLogger(AlertStore.class);

  private final String ALERTS_REF = "alerts";

  private final DatabaseReference databaseReference;

  @Inject
  public AlertStore(@Named(DATABASE_ROOT) String databaseRoot, FirebaseDatabase firebaseDatabase) {
    databaseReference = firebaseDatabase.getReference("/");
    databaseReference.setValue(new Test(), (databaseError, databaseReference) -> {
      LOG.info("CALLBACK!");
    });
  }

  public void saveAlert(Alert alert) {
    // TODO
  }

  public static class Test {
    public String name = "Name";
    public String value = "Value";

    public String getName() {
      return name;
    }

    public String getValue() {
      return value;
    }
  }
}
