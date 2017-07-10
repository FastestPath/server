package co.fastestpath.api.schedule.tripfinder;

import co.fastestpath.api.schedule.StopId;
import co.fastestpath.api.schedule.StopTime;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class TraversalResults {

  public static TraversalResults FAIL = new TraversalResults();

  private final Set<Node<StopId>> leaves;

  public TraversalResults() {
    this.leaves = new HashSet<>();
  }

  public void add(Node<StopId> leaf) {
    leaves.add(leaf);
  }

  public SortedSet<StopTime> getStops() {
    // TODO: traverse leaves, are reverse order
    return stops;
  }
}
