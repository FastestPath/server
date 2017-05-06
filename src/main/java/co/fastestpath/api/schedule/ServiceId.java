package co.fastestpath.api.schedule;

public class ServiceId {

  private final String value;

  public ServiceId(String value) {
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

    ServiceId stopId = (ServiceId) o;

    return value != null ? value.equals(stopId.value) : stopId.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
