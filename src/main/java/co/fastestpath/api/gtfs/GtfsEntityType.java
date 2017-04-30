package co.fastestpath.api.gtfs;

import co.fastestpath.api.gtfs.models.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public enum GtfsEntityType {
  STOPS("stops.txt", GtfsStop.class),
  ROUTES("routes.txt", GtfsRoute.class),
  TRIPS("trips.txt", GtfsTrip.class),
  STOP_TIMES("stop_times.txt", GtfsStopTime.class),
  CALENDAR("calendar.txt", GtfsCalendar.class),
  CALENDAR_DATES("calendar_dates.txt", GtfsCalendarDate.class);

  private final String filename;

  private final Class<?> clazz;

  private final Path path;

  public static Stream<GtfsEntityType> stream() {
    return Stream.of(values());
  }

  GtfsEntityType(String filename, Class<?> clazz) {
    this.filename = filename;
    this.clazz = clazz;
    this.path = Paths.get(filename);
  }

  public Path getPath() {
    return path;
  }

  public String getFilename() {
    return filename;
  }

  public Class<?> getClazz() {
    return clazz;
  }
}
