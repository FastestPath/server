package co.fastestpath.api.manager;

import co.fastestpath.api.FastestPathApplication;
import co.fastestpath.api.utils.ImmutableCollectors;
import com.google.inject.Injector;
import io.dropwizard.lifecycle.Managed;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Comparator;
import java.util.Set;

@Singleton
public class FastestPathManager implements Managed {

  private static final Comparator<FastestPathManaged> COMPARATOR =
      Comparator.comparingInt(FastestPathManaged::getPriority);

  private final Set<FastestPathManaged> managedClasses;

  @Inject
  public FastestPathManager(Injector injector) {
    this.managedClasses = createManagedClassInstances(injector, FastestPathApplication.class);
  }

  @Override
  public void start() throws Exception {
    for (FastestPathManaged managedClass : managedClasses) {
      managedClass.start();
    }
  }

  @Override
  public void stop() throws Exception {
    for (FastestPathManaged managedClass : managedClasses) {
      managedClass.stop();
    }
  }

  private static Set<FastestPathManaged> createManagedClassInstances(Injector injector, Class<?> entryPoint) {
    return ManagedClassScanner.scanForClasses(entryPoint).stream()
        .map(injector::getInstance)
        .sorted(COMPARATOR)
        .collect(ImmutableCollectors.toSet());
  }
}
