package co.fastestpath.api.schedule;

import co.fastestpath.api.ImmutableCollectors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CalendarMap {

  private final Map<ServiceId, Calendar> serviceIdMap;

  public static CalendarMap create(Set<Calendar> calendars) {
    Map<ServiceId, Calendar> map = new HashMap();
    calendars.forEach((calendar) -> map.put(calendar.getServiceId(), calendar));
    return new CalendarMap(map);
  }

  private CalendarMap(Map<ServiceId, Calendar> serviceIdMap) {
    this.serviceIdMap = Collections.unmodifiableMap(serviceIdMap);
  }

  public Set<ServiceId> findAvailableServices(CalendarDate calendarDate) {
    return serviceIdMap.values().stream()
        .filter((calendar) -> calendar.isAvailable(calendarDate))
        .map(Calendar::getServiceId)
        .collect(ImmutableCollectors.toSet());
  }

  public Calendar get(ServiceId serviceId) {
    return serviceIdMap.get(serviceId);
  }
}
