package co.fastestpath.api.schedule.mocks;

import co.fastestpath.api.schedule.Schedule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MockInjector {

  private static final Injector INJECTOR = Guice.createInjector();

  private MockInjector() {}

  public static Injector setup() {

    // registers schedule providers with this injector
    INJECTOR.getInstance(Schedule.class);

    // TODO: populate with mocks
    Schedule.clear()
        .agencies(MockAgencies.INSTANCE);

    return INJECTOR;
  }
}
