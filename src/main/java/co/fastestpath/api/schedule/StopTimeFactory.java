package co.fastestpath.api.schedule;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.gtfs.GtfsStopTime;

import java.util.List;
import java.util.Set;

public class StopTimeFactory {

  private StopTimeFactory() {}

  public static Set<StopTime> create(List<GtfsStopTime> stopTimes) {
    return stopTimes.stream()
        .map(StopTimeFactory::createStopTime)
        .collect(ImmutableCollectors.toSet());
  }

  private static StopTime createStopTime(GtfsStopTime stopTime) {
    return StopTime.builder()
        .tripId(new TripId(stopTime.getTripId()))
        .arrivalTime(stopTime.getArrivalTime())
        .
  }
}
