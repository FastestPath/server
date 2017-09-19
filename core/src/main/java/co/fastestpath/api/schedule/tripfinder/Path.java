package co.fastestpath.api.schedule.tripfinder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Path<T> {

  private final List<T> nodes;

  static <T> Path create(Node<T> leaf) {
    List<T> nodes = new LinkedList<>();

    // traverse from leaf to root
    while (leaf.getParent() != null) {
      nodes.add(leaf.getValue());
      leaf = leaf.getParent();
    }

    // add root node
    nodes.add(leaf.getValue());

    // reverse order, root to leaf
    Collections.reverse(nodes);

    return new Path<>(nodes);
  }

  private Path(List<T> nodes) {
    this.nodes = Collections.unmodifiableList(nodes);
  }

  public List<T> getNodes() {
    return nodes;
  }

  public int size() {
    return nodes.size();
  }
}
