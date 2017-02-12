package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.StopTime;
import co.fastestpath.api.schedule.models.Trip;

import java.util.ArrayList;
import java.util.List;

class TripPartitioner {

  public static List<Trip> createSubTrips(Trip trip) {
    List<StopTime> stopTimes = trip.getStopTimes();
    List<Trip> allTrips = new ArrayList<>();
    for (int i = stopTimes.size(); i >= 2; i--) {
      Trip subTrip = new Trip(stopTimes.subList(0, i));
      allTrips.add(subTrip);
    }
    return allTrips;
  }

  private TripPartitioner() {
  }
}
