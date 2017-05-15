package co.fastestpath.api.schedule;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.Set;

public class CalendarMap {

  private final BiMap<ServiceId, Calendar> map;

  public static CalendarMap create(Set<Calendar> calendars) {
    BiMap<ServiceId, Calendar> map = HashBiMap.create(calendars.size());
    calendars.forEach((calendar) -> map.put(calendar.getServiceId(), calendar));
    return new CalendarMap(map);
  }

  private CalendarMap(BiMap<ServiceId, Calendar> map) {
    this.map = ImmutableBiMap.copyOf(map);
  }

  public Calendar get(ServiceId serviceId) {
    return map.get(serviceId);
  }
}
