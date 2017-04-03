package co.fastestpath.api.schedule;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.time.Instant;

public class GoogleDirectionsApi {

  public static final String GOOGLE_API_KEY = "AIzaSyAgC7Fmr4yY65x_WTQWy1KFb6iYx0Z7-cY";

  private static final Logger LOG = LoggerFactory.getLogger(GoogleDirectionsApi.class);

  private static TravelMode MODE = TravelMode.TRANSIT;

  private static final GeoApiContext GEO_API_CONTEXT = new GeoApiContext()
      .setApiKey(GOOGLE_API_KEY);

  public static DirectionsResult fetch(LatLng origin, LatLng destination, Instant time) {
    try {
      return DirectionsApi.newRequest(GEO_API_CONTEXT)
          .origin(origin)
          .destination(destination)
          .mode(MODE)
          .departureTime(new org.joda.time.Instant(time.toEpochMilli()))
          .await();
    } catch (Exception e) {
      LOG.error("Failed to fetch directions from Google.", e);
      return null;
    }
  }
}
