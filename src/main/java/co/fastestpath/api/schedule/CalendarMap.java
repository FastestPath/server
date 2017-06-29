package co.fastestpath.api.schedule;

import co.fastestpath.api.ImmutableCollectors;
import com.google.common.collect.*;

import java.time.DayOfWeek;
import java.util.Set;

public class CalendarMap {

  private final BiMap<ServiceId, Calendar> serviceIdMap;

  public static CalendarMap create(Set<Calendar> calendars) {
    BiMap<ServiceId, Calendar> map = HashBiMap.create(calendars.size());
    calendars.forEach((calendar) -> map.put(calendar.getServiceId(), calendar));
    return new CalendarMap(map);
  }

  private CalendarMap(BiMap<ServiceId, Calendar> serviceIdMap) {
    this.serviceIdMap = ImmutableBiMap.copyOf(serviceIdMap);
  }

  public Set<Calendar> forDate(CalendarDate calendarDate) {
    serviceIdMap.values().stream()
        .filter((calendar) -> calendar.isServiceAvailable(calendarDate))
        .collect(ImmutableCollectors.toSet());
  }

  public Calendar get(ServiceId serviceId) {
    return serviceIdMap.get(serviceId);
  }
}
