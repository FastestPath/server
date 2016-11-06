package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.Schedule;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Locale;
import java.util.Optional;

@Singleton
class ScheduleFetcher {

  private static final Logger LOG = LoggerFactory.getLogger(ScheduleFetcher.class);

  private static final String PATH_DIRECTORY = "http://data.trilliumtransit.com/gtfs/path-nj-us/";
  private static final String ZIP_FILENAME = "path-nj-us.zip";
  private static final String STOP_IDS = "stops.txt";
  private static final String STOP_TIMES = "stop_times.txt";
  private static final String ELEMENT_SELECTOR = "tr > td:eq(2)";
  
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd mm:ss", Locale.ENGLISH);
  
  private static final URL ZIP_URL = createUrl();

  private final ScheduleFactory scheduleFactory;

  private final Path resourcesPath;
  private final Path zipPath;
  private final Path csvStopIds;
  private final Path csvStopTimes;

  @Inject
  public ScheduleFetcher(@Named("resources") Path resourcesPath, ScheduleFactory scheduleFactory) {
    this.scheduleFactory = scheduleFactory;
    this.resourcesPath = resourcesPath;
    this.zipPath = Paths.get(resourcesPath + "/" + ZIP_FILENAME);
    this.csvStopIds = Paths.get(resourcesPath + "/" + STOP_IDS);
    this.csvStopTimes = Paths.get(resourcesPath + "/" + STOP_TIMES);
  }

  public Optional<Schedule> fetch() throws ScheduleFetcherException {
    return fetch(null);
  }

  public Optional<Schedule> fetch(Instant currentModifiedOn) throws ScheduleFetcherException {
    LOG.info("Fetching schedule modifiedOn date...");
    Instant modifiedOn = fetchModifiedOn();
    LOG.info("Schedule last updated {}.", modifiedOn);

    if (currentModifiedOn != null && !modifiedOn.isAfter(currentModifiedOn)) {
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
    try {
      HttpURLConnection connection = (HttpURLConnection) ZIP_URL.openConnection();
      int responseCode = connection.getResponseCode();
      if (responseCode != HttpsURLConnection.HTTP_OK) {
        throw new ScheduleFetcherException("Bad response code when fetching zip, " + responseCode);
      }

      try (InputStream inputStream = connection.getInputStream()) {
        Files.copy(inputStream, zipPath);
      }
    } catch (IOException e) {
      throw new ScheduleFetcherException("Unable to open a connection to " + ZIP_URL.toString(), e);
    }

    try {
      ZipFile zipFile = new ZipFile(zipPath.toString());
      zipFile.extractFile(STOP_IDS, resourcesPath.toString());
      zipFile.extractFile(STOP_TIMES, resourcesPath.toString());
    } catch (ZipException e) {
      throw new ScheduleFetcherException("Failed to unzip stop times.", e);
    }

    try {
      Files.delete(zipPath);
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to delete zip.", e);
    }

    Schedule schedule;
    try {
      schedule = scheduleFactory.createFromCSV(csvStopIds, csvStopTimes, modifiedOn);
      Files.delete(csvStopIds);
      Files.delete(csvStopTimes);
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to read csv.", e);
    }

    LOG.info("Successfully fetched latest schedule.");
    return Optional.of(schedule);
  }

  public static Instant fetchModifiedOn() throws ScheduleFetcherException {
    Document document;
    try {
      document = Jsoup.connect(PATH_DIRECTORY).get();
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to read PATH directory at " + PATH_DIRECTORY, e);
    }

    Element element = document.select(ELEMENT_SELECTOR).get(1);
    String modifiedOnString = element.text();
    Instant modifiedOn;
    try {
      modifiedOn = DATE_FORMAT.parse(modifiedOnString).toInstant();
    } catch (ParseException e) {
      throw new ScheduleFetcherException("Unable to parse date, " +  modifiedOnString, e);
    }

    return modifiedOn;
  }

  private static URL createUrl() {
    String url = PATH_DIRECTORY + "/" + ZIP_FILENAME;
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Malformed URL to path zip " + url, e);
    }
  }
}