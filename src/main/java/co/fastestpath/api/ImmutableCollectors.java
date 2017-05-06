package co.fastestpath.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public final class ImmutableCollectors {

  private ImmutableCollectors() {}

  public static <T> Collector<T, ?, List<T>> toList() {
    return collectingAndThen(Collectors.toList(), ImmutableList::copyOf);
  }

  public static <T> Collector<T, ?, Set<T>> toSet() {
    return collectingAndThen(Collectors.toSet(), ImmutableSet::copyOf);
  }
}

