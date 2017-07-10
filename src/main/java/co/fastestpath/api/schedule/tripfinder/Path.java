package co.fastestpath.api.schedule.tripfinder;

import com.google.common.collect.ImmutableSortedSet;

import java.util.SortedSet;
import java.util.TreeSet;

public class Path<T> {

  private final SortedSet<T> nodes;

  static <T> Path create(Node<T> leaf) {
    TreeSet<T> nodes = new TreeSet<>();

    // traverse from leaf to root
    while (leaf.getParent() != null) {
      nodes.add(leaf.getValue());
      leaf = leaf.getParent();
    }

    // reverse order, root to leaf
    return new Path(nodes.descendingSet());
  }

  private Path(SortedSet<T> nodes) {
    this.nodes = ImmutableSortedSet.copyOf(nodes);
  }

  public SortedSet<T> getNodes() {
    return nodes;
  }

  public int size() {
    return nodes.size();
  }
}
