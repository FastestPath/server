package co.fastestpath.api.schedule

enum class PickupDropoffType(val value: Int) {
  REGULARLY_SCHEDULED(0),
  NOT_AVAILABLE(1),
  PHONE_AGENCY(2),
  COORDINATE_WITH_DRIVER(2);

  companion object {
    fun of(value: Int) = values().first { it.value == value }
  }
}
