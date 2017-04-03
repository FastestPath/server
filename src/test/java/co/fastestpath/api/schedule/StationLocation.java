package co.fastestpath.api.schedule;

import com.google.maps.model.LatLng;

class StationLocation {

  public static final LatLng THIRTY_THIRD_STREET = new LatLng(40.748541, -73.988553);

  public static final LatLng TWENTY_THIRD_STREET = new LatLng(40.742738, -73.99263);

  public static final LatLng FOURTEENTH_STREET = new LatLng(40.737096, -73.996911);

  public static final LatLng NINTH_STREET = new LatLng(40.734275, -73.998713);

  public static final LatLng CHRISTOPHER_STREET = new LatLng(40.733047, -74.007061);

  public static final LatLng HOBOKEN = new LatLng(40.734942, -74.027617);

  private final LatLng latLng;

  private StationLocation(double latitude, double longitude) {
    this.latLng = new LatLng(latitude, longitude);
  }
}
