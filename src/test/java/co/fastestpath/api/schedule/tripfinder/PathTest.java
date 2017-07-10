package co.fastestpath.api.schedule.tripfinder;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PathTest {

  private static final Node<String> NODE_ROOT = Node.createRoot("root");
  private static final Node<String> NODE_A = Node.create(NODE_ROOT, "A");
  private static final Node<String> NODE_B = Node.create(NODE_A, "B");
  private static final Node<String> NODE_C = Node.create(NODE_B, "C");

  @Test
  public void testPathCreation() {
    Path path = Path.create(NODE_C);
    assertEquals(path.size(), 4);
  }
}