package co.fastestpath.api.gtfs;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Singleton
class GtfsArchiveFetcher {

  private static final Logger LOG = LoggerFactory.getLogger(GtfsArchiveFetcher.class);

  private final Path resourcesPath;

  private final Path archivePath;

  @Inject
  public GtfsArchiveFetcher(@Named("resources") Path resourcesPath) {
    this.resourcesPath = resourcesPath;
    this.archivePath = Paths.get(resourcesPath + "/" + GtfsArchive.NAME);
  }

  public synchronized GtfsArchive fetch() throws GtfsArchiveFetchingException {

    LOG.info("Creating empty resources directory at {}...", resourcesPath);
    createEmptyDirectory(resourcesPath);
    LOG.info("Created empty resources directory.");

    URL archiveUrl = GtfsArchive.URL;
    LOG.info("Fetching schedule from {}...", archiveUrl);
    downloadArchive(archiveUrl, archivePath);
    LOG.info("Fetched schedule.");

    LOG.info("Extracting files from archive to {}...", resourcesPath);
    extractAllFilesFromArchive(resourcesPath, archivePath);
    LOG.info("Extracted all files.");

    GtfsArchive.Builder builder = GtfsArchive.builder();
    GtfsEntityType.stream()
        .filter((entityType) -> Files.exists(entityType.getPath(resourcesPath)))
        .forEach((entityType) -> builder.add(entityType, entityType.getPath(resourcesPath).toFile()));

    return builder.build();
  }

  private static void extractAllFilesFromArchive(Path resourcesPath, Path archivePath)
      throws GtfsArchiveFetchingException {
    try {
      ZipFile zipFile = new ZipFile(archivePath.toString());
      zipFile.extractAll(resourcesPath.toString());
    } catch (ZipException e) {
      throw new GtfsArchiveFetchingException("Failed to unzip archive.", e);
    }
  }

  private static void downloadArchive(URL archiveUrl, Path savePath) throws GtfsArchiveFetchingException {
    try (InputStream inputStream = openInputStream(archiveUrl)) {
      Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new GtfsArchiveFetchingException("Failed to download archive from " + archiveUrl, e);
    }
  }

  private static InputStream openInputStream(URL archiveUrl) throws GtfsArchiveFetchingException {
    try {
      HttpURLConnection connection = (HttpURLConnection) archiveUrl.openConnection();
      int responseCode = connection.getResponseCode();
      if (responseCode != HttpsURLConnection.HTTP_OK) {
        throw new GtfsArchiveFetchingException("Bad response code when fetching zip, " + responseCode);
      }
      return connection.getInputStream();
    } catch (IOException e) {
      throw new GtfsArchiveFetchingException("Unable to open a connection to " + archiveUrl, e);
    }
  }

  private static void createEmptyDirectory(Path resourcesPath) throws GtfsArchiveFetchingException {
    if (Files.isDirectory(resourcesPath)) {
      try {
        FileUtils.cleanDirectory(resourcesPath.toFile());
      } catch (IOException e) {
        throw new GtfsArchiveFetchingException("Failed to empty resource directory.", e);
      }
      return;
    }

    try {
      Files.createDirectory(resourcesPath);
    } catch (IOException e) {
      throw new GtfsArchiveFetchingException("Failed to create resources directory.", e);
    }
  }
}