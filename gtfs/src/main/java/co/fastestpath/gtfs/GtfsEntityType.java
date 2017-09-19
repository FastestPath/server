package co.fastestpath.api.gtfs;

import com.google.common.collect.ImmutableList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class GtfsEntityType<T extends GtfsEntity> {

  public static GtfsEntityType<GtfsAgency> AGENCY = new GtfsEntityType("agency.txt", GtfsAgency.class);
  public static GtfsEntityType<GtfsStop> STOPS = new GtfsEntityType("stops.txt", GtfsStop.class);
  public static GtfsEntityType<GtfsRoute> ROUTES = new GtfsEntityType("routes.txt", GtfsRoute.class);
  public static GtfsEntityType<GtfsTrip> TRIPS = new GtfsEntityType("trips.txt", GtfsTrip.class);
  public static GtfsEntityType<GtfsStopTime> STOP_TIMES = new GtfsEntityType("stop_times.txt", GtfsStopTime.class);
  public static GtfsEntityType<GtfsCalendar> CALENDAR = new GtfsEntityType("calendar.txt", GtfsCalendar.class);
  public static GtfsEntityType<GtfsCalendarDate> CALENDAR_DATES = new GtfsEntityType("calendar_dates.txt", GtfsCalendarDate.class);

  private static final List<GtfsEntityType> AS_LIST = ImmutableList.of(
      AGENCY,
      STOPS,
      ROUTES,
      TRIPS,
      STOP_TIMES,
      CALENDAR,
      CALENDAR_DATES
  );

  public static void forEach(Consumer<? super GtfsEntityType> consumer) {
    AS_LIST.stream().forEach(consumer);
  }

  public static Stream<GtfsEntityType> stream() {
    return AS_LIST.stream();
  }

  private final String filename;

  private final Class<T> clazz;

  private GtfsEntityType(String filename, Class<T> clazz) {
    this.filename = filename;
    this.clazz = clazz;
  }

  public Path getPath(Path directory) {
    return Paths.get(directory + "/" + filename);
  }

  public Class<T> getClazz() {
    return clazz;
  }
}
