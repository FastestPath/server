package co.fastestpath.api.gtfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

class GtfsArchiveDateChecker {

  private static final Logger LOG = LoggerFactory.getLogger(GtfsArchiveDateChecker.class);

  private static Instant previousModifiedOn;

  public static boolean isUpToDate() {
    Instant currentModifiedOn;
    try {
      currentModifiedOn = GtfsArchiveDateFetcher.fetchModifiedOn();
    } catch (GtfsArchiveDateFetchingException e) {
      LOG.error("Failed to fetch archive modification date.");
      return false;
    }

    if (previousModifiedOn == null) {
      previousModifiedOn = currentModifiedOn;
      return false;
    }

    if (currentModifiedOn.isAfter(previousModifiedOn)) {
      previousModifiedOn = currentModifiedOn;
      return false;
    }

    return true;
  }

  private GtfsArchiveDateChecker() {}
}
