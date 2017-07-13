package co.fastestpath.api.schedule.tripfinder;

import org.testng.annotations.Test;

import java.util.List;

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

    List<String> nodes = path.getNodes();
    assertEquals(nodes.get(0), NODE_ROOT.getValue());
    assertEquals(nodes.get(3), NODE_C.getValue());
  }
}