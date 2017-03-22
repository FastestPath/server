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
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.assertEquals;

public class TripTimeTest {
    /**
     * API Key: AIzaSyAgC7Fmr4yY65x_WTQWy1KFb6iYx0Z7-cY
     **/

    private static final String KEY_STRING = "AIzaSyAgC7Fmr4yY65x_WTQWy1KFb6iYx0Z7-cY";
    private static final GeoApiContext GEO_API_CONTEXT = new GeoApiContext().setApiKey(KEY_STRING);
    private static final String TEST_CONFIG = "test.yml";
    private GuiceBundle<FastestPathConfiguration> guiceBundle;
    private Instant elevenHoursLater;

    @Rule
    public final DropwizardAppRule<FastestPathConfiguration> RULE = new DropwizardAppRule<>(FastestPathApplication.class,
        ResourceHelpers.resourceFilePath(TEST_CONFIG));

    @Before
    public void setup() {
        elevenHoursLater = Instant.now().plus(23, ChronoUnit.HOURS);
        FastestPathApplication application = RULE.getApplication();
        guiceBundle = application.guiceBundle;
    }

    @Test
    public void hobokenTo33rdTest() throws Exception {
        DirectionsResult result = DirectionsApi.newRequest(GEO_API_CONTEXT)
            .origin(new LatLng(40.734940, -74.027605))
            .destination(new LatLng(40.748534, -73.988544))
            .mode(TravelMode.TRANSIT)
            .departureTime(new org.joda.time.Instant(elevenHoursLater.toEpochMilli()))
            .await();

        DirectionsLeg leg = result.routes[0].legs[0];
        assertEquals(leg.distance.toString(), "3.0 mi");

        Injector injector = guiceBundle.getInjector();
        ScheduleManager scheduleManager = injector.getInstance(ScheduleManager.class);
        Thread.sleep(8000);

        Departure departure = scheduleManager.getDeparture(StationName.HOBOKEN, StationName.THIRTY_THIRD_STREET, elevenHoursLater);
        java.time.Duration between = java.time.Duration.between(departure.departureTime, departure.arrivalTime);
        Assert.assertEquals(leg.duration.humanReadable, between.toMinutes() + " mins");
    }
}
