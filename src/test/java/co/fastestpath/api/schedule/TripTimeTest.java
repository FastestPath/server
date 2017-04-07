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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;

public class TripTimeTest {
    /**
     * API Key: AIzaSyAgC7Fmr4yY65x_WTQWy1KFb6iYx0Z7-cY
     **/

    private static final String KEY_STRING = "AIzaSyAgC7Fmr4yY65x_WTQWy1KFb6iYx0Z7-cY";
    private static final GeoApiContext GEO_API_CONTEXT = new GeoApiContext().setApiKey(KEY_STRING);
    private static final String TEST_CONFIG = "test.yml";
    private GuiceBundle<FastestPathConfiguration> guiceBundle;

    // Times
    Instant[] myInstants;
    private Instant now;
    private Instant fiveAm;
    private Instant elevenFortyFivePm;
    private Instant elevenHoursLater;
    private Instant weekEnd;
//    private Instant weekDay;
//    private Instant endOfMonth;


    @Rule
    public final DropwizardAppRule<FastestPathConfiguration> RULE = new DropwizardAppRule<>(FastestPathApplication.class,
        ResourceHelpers.resourceFilePath(TEST_CONFIG));

    @Before
    public void setup() throws Exception {
        now = Instant.now();
        elevenHoursLater = Instant.now().plus(23, ChronoUnit.HOURS);
        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateFormat dateUtcFormat = new SimpleDateFormat("u");

        TimeZone.setDefault(TimeZone.getTimeZone("EST"));

        Date fiveAmDate = new Date();
        fiveAmDate.setHours(5);
        fiveAmDate.setMinutes(0);
        fiveAmDate.setSeconds(0);

        Date elevenFortyFiveDate = new Date();
        elevenFortyFiveDate.setHours(23);
        elevenFortyFiveDate.setMinutes(45);
        elevenFortyFiveDate.setSeconds(0);

        Date weekendDate = new Date();

        int dayIndex = Integer.parseInt(dateUtcFormat.format(weekendDate));
        if(dayIndex != 6 && dayIndex != 7){
            System.out.println(weekendDate.getTime());
            weekendDate.setTime(weekendDate.getTime() + 3600 * 1000 * 24);
        }
        System.out.println(dateUtcFormat.format(weekendDate));
        System.exit(0);

        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        fiveAm = Instant.parse(utcFormat.format(fiveAmDate));
        elevenFortyFivePm = Instant.parse(utcFormat.format(elevenFortyFiveDate));
        myInstants = new Instant[]{now, fiveAm, elevenFortyFivePm, elevenHoursLater};

        FastestPathApplication application = RULE.getApplication();
        guiceBundle = application.guiceBundle;
    }

    @Test
    public void hobokenTo33rdTest() throws Exception {
        for (Instant i : myInstants) {
            DirectionsResult result = DirectionsApi.newRequest(GEO_API_CONTEXT)
                .origin(new LatLng(40.734940, -74.027605))
                .destination(new LatLng(40.748534, -73.988544))
                .mode(TravelMode.TRANSIT)
                .departureTime(new org.joda.time.Instant(i.toEpochMilli()))
                .await();

            DirectionsLeg leg = result.routes[0].legs[0];
            assertEquals(leg.distance.toString(), "3.0 mi");

            Injector injector = guiceBundle.getInjector();
            ScheduleManager scheduleManager = injector.getInstance(ScheduleManager.class);
            Thread.sleep(8000);

            Departure departure = scheduleManager.getDeparture(StationName.HOBOKEN, StationName.THIRTY_THIRD_STREET, i);
            java.time.Duration between = java.time.Duration.between(departure.departureTime, departure.arrivalTime);
            Assert.assertEquals(i.toString(), leg.duration.humanReadable, between.toMinutes() + " mins");
        }
    }
}
