package co.fastestpath.api.schedule;

public class StopId {

  private final String value;

  public StopId(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    StopId stopId = (StopId) o;

    return value != null ? value.equals(stopId.value) : stopId.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
