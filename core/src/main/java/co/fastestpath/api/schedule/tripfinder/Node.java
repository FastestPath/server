package co.fastestpath.api.schedule.tripfinder;

class Node<T> {

  private final T value;

  private final Node<T> parent;

  private Node(Node<T> parent, T value) {
    this.parent = parent;
    this.value = value;
  }

  public static <T> Node<T> createRoot(T value) {
    return new Node<>(null, value);
  }

  public static <T> Node<T> create(Node<T> parent, T value) {
    return new Node<>(parent, value);
  }

  public Node<T> getParent() {
    return parent;
  }

  public T getValue() {
    return value;
  }
}
