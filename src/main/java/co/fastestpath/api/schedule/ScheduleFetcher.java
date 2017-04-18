package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.Schedule;
import co.fastestpath.api.schedule.models.Stop;
import co.fastestpath.api.schedule.models.StopTime;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Singleton
class ScheduleFetcher {

  public static final String SCHEDULE_ADDRESS = "http://data.trilliumtransit.com/gtfs/path-nj-us/";

  private static final Logger LOG = LoggerFactory.getLogger(ScheduleFetcher.class);

  private static final String SCHEDULE_ZIP = "path-nj-us.zip";

  private static final String STOP_IDS_CSV = "stops.txt";
  private static final String STOP_TIMES_CSV = "stop_times.txt";

  private static final URL ZIP_URL = createUrl();

  private final ScheduleCsvParser csvParser;

  private final Path resourcesPath;
  private final Path zipPath;

  private final Path stopIdsCsvPath;
  private final Path stopTimesCsvPath;

  @Inject
  public ScheduleFetcher(@Named("resources") Path resourcesPath, ScheduleCsvParser csvParser) {
    this.resourcesPath = resourcesPath;
    this.zipPath = Paths.get(resourcesPath + "/" + SCHEDULE_ZIP);
    this.stopIdsCsvPath = Paths.get(resourcesPath + "/" + STOP_IDS_CSV);
    this.stopTimesCsvPath = Paths.get(resourcesPath + "/" + STOP_TIMES_CSV);
    this.csvParser = csvParser;
  }

  public Optional<Schedule> fetchSchedule() throws ScheduleFetcherException, ScheduleParseException {
    return fetchSchedule(null);
  }

  public Optional<Schedule> fetchSchedule(Instant modifiedAfter) throws ScheduleFetcherException,
      ScheduleParseException {
    Instant modifiedOn = ScheduleDateFetcher.fetchModifiedOn();

    if (modifiedAfter != null && !modifiedOn.isAfter(modifiedAfter)) {
      LOG.info("Schedule is already update to date.");
      return Optional.empty();
    }

    if (!Files.isDirectory(resourcesPath)) {
      LOG.info("Resources directory does not exist. Creating...");
      try {
        Files.createDirectory(resourcesPath);
      } catch (IOException e) {
        throw new ScheduleFetcherException("Unable to create resources directory.", e);
      }
      LOG.info("Created resources directory at {}", resourcesPath.toAbsolutePath());
    }

    LOG.info("Fetching schedule...");
    try (InputStream inputStream = fetchScheduleZip()) {
      Files.copy(inputStream, zipPath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to download schedule zip from " + ZIP_URL, e);
    }

    try {
      extractCsvsFromZip(zipPath, resourcesPath);
    } catch (ZipException e) {
      throw new ScheduleFetcherException("Failed to unzip stop times.", e);
    }

    try {
      Files.delete(zipPath);
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to delete zip.", e);
    }

    List<Stop> stops = csvParser.parseCsv(stopIdsCsvPath, Stop.class);
    List<StopTime> stopTimes = csvParser.parseCsv(stopTimesCsvPath, StopTime.class);

    Schedule schedule = ScheduleFactory.create(stops, stopTimes, modifiedOn);

    try {
      Files.delete(stopIdsCsvPath);
      Files.delete(stopTimesCsvPath);
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to delete stop Ids and/or stop times CSVs.");
    }

    LOG.info("Successfully fetched latest schedule.");
    return Optional.of(schedule);
  }

  private static void extractCsvsFromZip(Path zipPath, Path resourcesPath) throws ZipException {
    ZipFile zipFile = new ZipFile(zipPath.toString());
    zipFile.extractFile(STOP_IDS_CSV, resourcesPath.toString());
    zipFile.extractFile(STOP_TIMES_CSV, resourcesPath.toString());
  }

  private static InputStream fetchScheduleZip() throws ScheduleFetcherException {
    try {
      HttpURLConnection connection = (HttpURLConnection) ZIP_URL.openConnection();
      int responseCode = connection.getResponseCode();
      if (responseCode != HttpsURLConnection.HTTP_OK) {
        throw new ScheduleFetcherException("Bad response code when fetching zip, " + responseCode);
      }
      return connection.getInputStream();
    } catch (IOException e) {
      throw new ScheduleFetcherException("Unable to open a connection to " + ZIP_URL.toString(), e);
    }
  }

  private static URL createUrl() {
    String url = SCHEDULE_ADDRESS + "/" + SCHEDULE_ZIP;
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Malformed URL to path zip " + url, e);
    }
  }
}