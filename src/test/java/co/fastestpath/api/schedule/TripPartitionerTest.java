package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.StopTime;
import co.fastestpath.api.schedule.models.Trip;
import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TripPartitionerTest {

  private static final StopTime STOP_TIME_A = StopTime.builder().build();
  private static final StopTime STOP_TIME_B = StopTime.builder().build();
  private static final StopTime STOP_TIME_C = StopTime.builder().build();
  private static final StopTime STOP_TIME_D = StopTime.builder().build();


  private static final List<StopTime> STOP_TIMES = ImmutableList.of(
      STOP_TIME_A,
      STOP_TIME_B,
      STOP_TIME_C,
      STOP_TIME_D
  );

  /**
   * Should return [ [A, B, C, D], [A, B, C], [A, B] ]
   */
  @Test
  public void testCreateSubTrips() throws Exception {
    Trip trip = new Trip(STOP_TIMES);
    List<Trip> subTrips = TripPartitioner.createSubTrips(trip);
    assertTrue(subTrips.size() == 3);
    assertTrue(subTrips.get(0).getStopTimes().size() == 4);
    assertTrue(subTrips.get(1).getStopTimes().size() == 3);
    assertTrue(subTrips.get(2).getStopTimes().size() == 2);
  }
}