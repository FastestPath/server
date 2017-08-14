package co.fastestpath.api.schedule.route;

public class RouteId {

  private final String value;

  public RouteId(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RouteId stopId = (RouteId) o;

    return value != null ? value.equals(stopId.value) : stopId.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
