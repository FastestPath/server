package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.StopTime;
import co.fastestpath.api.schedule.models.Trip;

import java.util.ArrayList;
import java.util.List;

class TripPartitioner {

  public static List<Trip> createSubTrips(Trip trip) {
    List<StopTime> stopTimes = trip.getStopTimes();
    List<Trip> allTrips = new ArrayList<>();
    int numberOfStopTimes = stopTimes.size();
    for (int fromIndex = 0; numberOfStopTimes - fromIndex >= 2; fromIndex++) {
      for (int toIndex = numberOfStopTimes; toIndex - fromIndex >= 2; toIndex--) {
        Trip subTrip = new Trip(stopTimes.subList(fromIndex, toIndex));
        allTrips.add(subTrip);
      }
    }
    return allTrips;
  }

  private TripPartitioner() {
  }
}
