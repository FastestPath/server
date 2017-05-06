package co.fastestpath.api.schedule;

import com.google.maps.model.LatLng;

enum StationLocation {

  THIRTY_THIRD_STREET(new LatLng(40.748541, -73.988553)),
  TWENTY_THIRD_STREET(new LatLng(40.742738, -73.99263)),
  FOURTEENTH_STREET(new LatLng(40.737096, -73.996911)),
  NINTH_STREET(new LatLng(40.734275, -73.998713)),
  CHRISTOPHER_STREET(new LatLng(40.733047, -74.007061)),
  HOBOKEN(new LatLng(40.734942, -74.027617));

  private final LatLng latLng;

  public static StationLocation fromStationName(StationName stationName) {
    return Enum.valueOf(StationLocation.class, stationName.name());
  }

  StationLocation(LatLng latLng) {
    this.latLng = latLng;
  }

  public LatLng getLatLng() {
    return latLng;
  }
}
