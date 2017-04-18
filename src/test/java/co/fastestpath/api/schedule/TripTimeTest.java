package co.fastestpath.api.schedule;

import co.fastestpath.api.FastestPathApplication;
import co.fastestpath.api.FastestPathConfiguration;
import co.fastestpath.api.schedule.models.Departure;
import co.fastestpath.api.schedule.models.StationName;
import com.google.inject.Injector;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TripTimeTest {

  public static final String TEST_CONFIG = "test.yml";

  @Rule
  public DropwizardAppRule<FastestPathConfiguration> RULE = new DropwizardAppRule<>(FastestPathApplication.class,
      ResourceHelpers.resourceFilePath(TEST_CONFIG));

  private ScheduleManager scheduleManager;

  private Instant now;

  @Before
  public void setup() throws Exception {
    FastestPathApplication application = RULE.getApplication();
    Injector injector = application.guiceBundle.getInjector();

    scheduleManager = injector.getInstance(ScheduleManager.class);

    while (scheduleManager.isFetching()) {
      Thread.sleep(1000);
    }
  }

  @BeforeMethod
  public void beforeMethod() {
    now = Instant.now();
  }

  @Test
  public void testHobokenToChristopher() {
    compareWithGoogle(StationName.HOBOKEN, StationName.CHRISTOPHER_STREET);
  }

  private void compareWithGoogle(StationName origin, StationName destination) {

    LatLng originLatLng = StationLocation.fromStationName(origin).getLatLng();
    LatLng destinationLatLng = StationLocation.fromStationName(destination).getLatLng();

    DirectionsResult directions = GoogleDirectionsApi.fetch(originLatLng, destinationLatLng, Instant.now());
    DirectionsLeg leg = directions.routes[0].legs[0];

    assertEquals(directions.routes.length, 1);
    assertEquals(directions.routes[0].legs.length, 1);

    scheduleManager.fetchLatest(() -> {
      Optional<Departure> departureOptional = scheduleManager.getDeparture(origin, destination, now);

      if (!departureOptional.isPresent()) {
        fail("Departure result is empty.");
      }

      Departure departure = departureOptional.get();
      Duration tripDuration = Duration.between(departure.getDepartureTime(), departure.getArrivalTime());

      Assert.assertEquals(leg.duration.humanReadable, tripDuration.toMinutes() + " mins");
    });
  }
}
