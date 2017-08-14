package co.fastestpath.api.schedule.agency;

import co.fastestpath.api.ImmutableCollectors;
import co.fastestpath.api.gtfs.GtfsAgency;

import java.time.ZoneId;
import java.util.List;
import java.util.Set;

public class AgencyFactory {

  private AgencyFactory() {}

  public static final Set<Agency> create(List<GtfsAgency> agencies) {
    return agencies.stream()
        .map(AgencyFactory::createAgency)
        .collect(ImmutableCollectors.toSet());
  }

  private static Agency createAgency(GtfsAgency agency) {
    return Agency.builder()
        .id(new AgencyId(agency.getId()))
        .name(agency.getName())
        .url(agency.getUrl())
        .timeZone(ZoneId.of(agency.getTimezone()))
        .language(agency.getLanguage())
        .phone(agency.getPhone())
        .fareUrl(agency.getFareUrl())
        .email(agency.getEmail())
        .build();
  }
}
