package co.fastestpath.api.schedule;

import co.fastestpath.api.FastestPathApplication;
import co.fastestpath.api.FastestPathConfiguration;
import co.fastestpath.api.gtfs.GtfsArchiveManager;
import co.fastestpath.api.schedule.models.Departure;
import co.fastestpath.api.schedule.models.StationName;
import com.google.inject.Injector;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static org.testng.Assert.*;

public class TripTimeTest {

  public static final String TEST_CONFIG = "test.yml";

  @Rule
  public DropwizardAppRule<FastestPathConfiguration> RULE = new DropwizardAppRule<>(FastestPathApplication.class,
      ResourceHelpers.resourceFilePath(TEST_CONFIG));

  private GtfsArchiveManager scheduleManager;

  private Instant now;

  private boolean isSetup;

  @Before
  public void setup() throws Exception {

    if (isSetup) {
      return;
    }

    isSetup = true;
    FastestPathApplication application = RULE.getApplication();
    Injector injector = application.guiceBundle.getInjector();

    scheduleManager = injector.getInstance(GtfsArchiveManager.class);
    while (scheduleManager.isFetching()) {
      Thread.sleep(1000);
    }
  }

  @Before
  public void beforeEachTest() {
    now = Instant.now().plus(140, ChronoUnit.MINUTES);
  }

  @Test
  public void testTimeZone() {

    Date other = new DateTime().withTimeAtStartOfDay().toDate();

      Date startOfDay = new DateTime(DateTimeZone.forID("America/New_York"))
          .withTimeAtStartOfDay()
          .toDate();

      assertEquals(startOfDay.getTime(), other.getTime());
  }

  @Test
  public void testHobokenToChristopher() {
    compareWithGoogle(StationName.HOBOKEN, StationName.CHRISTOPHER_STREET);
  }

  private void compareWithGoogle(StationName origin, StationName destination) {

    LatLng originLatLng = StationLocation.fromStationName(origin).getLatLng();
    LatLng destinationLatLng = StationLocation.fromStationName(destination).getLatLng();

    DirectionsResult directions = GoogleDirectionsApi.fetch(originLatLng, destinationLatLng, now);

    assertNotNull(directions);
    assertEquals(directions.routes.length, 1);
    assertEquals(directions.routes[0].legs.length, 1);

    DirectionsLeg leg = directions.routes[0].legs[0];

    scheduleManager.fetchLatest(() -> {
      Optional<Departure> departureOptional = scheduleManager.getDeparture(origin, destination, now);

      if (!departureOptional.isPresent()) {
        fail("Departure result is empty.");
      }

      Departure departure = departureOptional.get();
      Duration tripDuration = Duration.ofSeconds(departure.getDepartureTime().toSeconds() - departure.getArrivalTime().toSeconds());

      assertEquals(tripDuration.toMinutes() + " mins", leg.duration.humanReadable);
    });
  }
}
