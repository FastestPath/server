package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.StopMap;
import co.fastestpath.api.schedule.StopTripMap;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

public class StopTraverserTest {

  @Mock
  private StopTripMap stopTripMap;

  @Mock
  private StopMap stopMap;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }



}