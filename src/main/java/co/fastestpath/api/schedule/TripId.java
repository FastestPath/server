package co.fastestpath.api.schedule;

public class TripId {

  private final String value;

  public TripId(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TripId stopId = (TripId) o;

    return value != null ? value.equals(stopId.value) : stopId.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
