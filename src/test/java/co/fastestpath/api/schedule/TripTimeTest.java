package co.fastestpath.api.schedule;

import co.fastestpath.api.FastestPathApplication;
import co.fastestpath.api.FastestPathConfiguration;
import co.fastestpath.api.schedule.models.Departure;
import co.fastestpath.api.schedule.models.StationName;
import com.google.inject.Injector;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static co.fastestpath.api.schedule.StationLocation.CHRISTOPHER_STREET;
import static co.fastestpath.api.schedule.StationLocation.HOBOKEN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TripTimeTest {

  public static final String TEST_CONFIG = "test.yml";

  public DropwizardAppRule<FastestPathConfiguration> RULE = new DropwizardAppRule<>(FastestPathApplication.class,
      ResourceHelpers.resourceFilePath(TEST_CONFIG));

  private ScheduleManager scheduleManager;

  private Instant now;

  @Before
  public void setup() {
    FastestPathApplication application = RULE.getApplication();
    Injector injector = application.guiceBundle.getInjector();

    scheduleManager = injector.getInstance(ScheduleManager.class);
  }

  @BeforeMethod
  public void beforeMethod() {
    now = Instant.now();
  }

  @Test
  public void testAllTripTimes() throws Exception {
    DirectionsResult directions = GoogleDirectionsApi.fetch(HOBOKEN, CHRISTOPHER_STREET, Instant.now());
    DirectionsLeg leg = directions.routes[0].legs[0];

    assertEquals(leg.distance.toString(), "3.0 mi");

    scheduleManager.fetchLatest(() -> {
      Optional<Departure> departureOptional = scheduleManager.getDeparture(StationName.HOBOKEN,
          StationName.THIRTY_THIRD_STREET, now);

      if (!departureOptional.isPresent()) {
        fail("Departure result is empty.");
      }

      Departure departure = departureOptional.get();
      Duration tripDuration = Duration.between(departure.getDepartureTime(), departure.getArrivalTime());

      Assert.assertEquals(leg.duration.humanReadable, tripDuration.toMinutes() + " mins");
    });
  }
}
