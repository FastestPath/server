package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.utils.ImmutableCollectors;

import java.util.HashSet;
import java.util.Set;

class TraversalResults {

  public static TraversalResults EMPTY = new TraversalResults();

  private final Set<Node<StopId>> leaves;

  public TraversalResults() {
    this.leaves = new HashSet<>();
  }

  public void add(Node<StopId> leaf) {
    leaves.add(leaf);
  }

  public Set<Path> getPaths() {
    return leaves.stream()
        .map(Path::create)
        .collect(ImmutableCollectors.toSet());
  }
}
