package co.fastestpath.api.schedule;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;

import java.time.ZoneId;
import java.util.Set;

public class AgencyMap {

  private final BiMap<AgencyId, Agency> map;

  // all agencies must share the same time zone
  private final ZoneId timeZone;

  public static AgencyMap fromAgencies(Set<Agency> agencies) {
    BiMap<AgencyId, Agency> map = HashBiMap.create(agencies.size());
    agencies.forEach((agency) -> map.put(agency.getId(), agency));
    return new AgencyMap(map);
  }

  private AgencyMap(BiMap<AgencyId, Agency> map) {
    this.map = ImmutableBiMap.copyOf(map);
    this.timeZone = map.values().stream()
        .findFirst()
        .get()
        .getTimeZone();
  }

  public Agency get(AgencyId agencyId) {
    return map.get(agencyId);
  }

  public Set<Agency> getAll() {
    return ImmutableSet.copyOf(map.values());
  }

  public ZoneId getFeedTimeZone() {
    return timeZone;
  }
}
