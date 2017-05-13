package co.fastestpath.api.schedule;

public class AgencyId {

  private final String value;

  public AgencyId(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AgencyId agencyId = (AgencyId) o;

    return value != null ? value.equals(agencyId.value) : agencyId.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
