package co.fastestpath.api.schedule;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.gtfs.GtfsStopTime;

import java.util.List;
import java.util.Set;

// TODO: test
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
        .arrivalTime(HhMmSs.create(stopTime.getArrivalTime()))
        .departureTime(HhMmSs.create(stopTime.getDepartureTime()))
        .sequence(stopTime.getStopSequence())
        .headsign(stopTime.getStopHeadSign())
        .pickupType(stopTime.getPickupType())
        .dropOffType(stopTime.getDropOffType())
        .shapeDistTraveled(stopTime.getShapeDistTraveled())
        .timePoint(TimePoint.from(stopTime.getTimePoint()))
        .build();
  }
}