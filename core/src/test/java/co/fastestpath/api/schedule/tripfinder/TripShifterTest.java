package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.StopTime;
import co.fastestpath.api.schedule.StopTimeMap;
import co.fastestpath.api.schedule.mocks.MockStopTimes;
import co.fastestpath.api.schedule.mocks.MockStops;
import co.fastestpath.api.schedule.mocks.MockTrips;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Provider;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class TripShifterTest {

  private TripShifter tripShifter;

  @BeforeMethod
  public void setup() {
    Set<StopTime> stopTimes = MockStopTimes.STOP_TIMES_RANDOMIZED;
    StopTimeMap stopTimeMap = StopTimeMap.create(stopTimes);

    Provider<StopTimeMap> stopTimeMapProvider = mock(Provider.class);
    when(stopTimeMapProvider.get()).thenReturn(stopTimeMap);

    tripShifter = new TripShifter(stopTimeMapProvider);
  }

  @Test
  public void testShiftToOrigin() {
    ShiftedTrip trip = tripShifter.shiftToOrigin(MockStops.STOP_C, MockTrips.TRIP_B);
    assertEquals(trip.next().getStopId(), MockStops.STOP_C);
  }

}