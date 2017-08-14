package co.fastestpath.api.schedule.mocks;

import co.fastestpath.api.schedule.StopId;
import co.fastestpath.api.schedule.StopTime;
import co.fastestpath.api.schedule.trip.TripId;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static co.fastestpath.api.schedule.mocks.MockStops.*;
import static co.fastestpath.api.schedule.mocks.MockTrips.TRIP_A;
import static co.fastestpath.api.schedule.mocks.MockTrips.TRIP_B;
import static co.fastestpath.api.schedule.mocks.MockTrips.TRIP_C;

public class MockStopTimes {

  public static final StopTime STOP_TIME_A_A = createMockStopTime(TRIP_A, STOP_A, 1);
  public static final StopTime STOP_TIME_A_B = createMockStopTime(TRIP_A, STOP_B, 2);
  public static final StopTime STOP_TIME_A_C = createMockStopTime(TRIP_A, STOP_C, 3);
  public static final StopTime STOP_TIME_A_D = createMockStopTime(TRIP_A, STOP_D, 4);
  public static final StopTime STOP_TIME_A_E = createMockStopTime(TRIP_A, STOP_E, 5);
  public static final StopTime STOP_TIME_A_F = createMockStopTime(TRIP_A, STOP_F, 6);

  public static final StopTime STOP_TIME_B_A = createMockStopTime(TRIP_B, STOP_A, 1);
  public static final StopTime STOP_TIME_B_B = createMockStopTime(TRIP_B, STOP_B, 2);
  public static final StopTime STOP_TIME_B_C = createMockStopTime(TRIP_B, STOP_C, 3);
  public static final StopTime STOP_TIME_B_D = createMockStopTime(TRIP_B, STOP_D, 4);
  public static final StopTime STOP_TIME_B_E = createMockStopTime(TRIP_B, STOP_E, 5);
  public static final StopTime STOP_TIME_B_F = createMockStopTime(TRIP_B, STOP_F, 6);

  public static final StopTime STOP_TIME_C_A = createMockStopTime(TRIP_C, STOP_A, 1);
  public static final StopTime STOP_TIME_C_B = createMockStopTime(TRIP_C, STOP_B, 2);
  public static final StopTime STOP_TIME_C_C = createMockStopTime(TRIP_C, STOP_C, 3);
  public static final StopTime STOP_TIME_C_D = createMockStopTime(TRIP_C, STOP_D, 4);
  public static final StopTime STOP_TIME_C_E = createMockStopTime(TRIP_C, STOP_E, 5);
  public static final StopTime STOP_TIME_C_F = createMockStopTime(TRIP_C, STOP_F, 6);

  public static final List<StopTime> STOP_TIMES_SORTED = ImmutableList.of(
      STOP_TIME_A_A, STOP_TIME_A_B, STOP_TIME_A_C, STOP_TIME_A_D, STOP_TIME_A_E, STOP_TIME_A_F,
      STOP_TIME_B_A, STOP_TIME_B_B, STOP_TIME_B_C, STOP_TIME_B_D, STOP_TIME_B_E, STOP_TIME_B_F,
      STOP_TIME_C_A, STOP_TIME_C_B, STOP_TIME_C_C, STOP_TIME_C_D, STOP_TIME_C_E, STOP_TIME_C_F
  );

  public static final Set<StopTime> STOP_TIMES_RANDOMIZED = randomizeStopTimes();

  private static Set<StopTime> randomizeStopTimes() {
    ArrayList<StopTime> list = new ArrayList<>(STOP_TIMES_SORTED);
    Collections.shuffle(list);
    return ImmutableSet.copyOf(list);
  }

  private static StopTime createMockStopTime(TripId tripId, StopId stopId, int sequence) {
    return StopTime.builder()
        .tripId(tripId)
        .stopId(stopId)
        .sequence(sequence)
        .build();
  }
}
