package co.fastestpath.api.managed;

import org.reflections.Reflections;

import java.util.*;

class ManagedClassScanner {

  public static Set<Class<? extends FastestPathManaged>> scanForClasses(Class entryPoint) {
    String path = entryPoint.getPackage().getName();
    Reflections reflections = new Reflections(path);
    return reflections.getSubTypesOf(FastestPathManaged.class);
  }

  private ManagedClassScanner() {}
}
