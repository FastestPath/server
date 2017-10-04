package co.fastestpath.api.bootstrap.gtfs.archive.date

import org.slf4j.LoggerFactory
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArchiveDateChecker @Inject constructor(private val archiveDateFetcher: ArchiveDateFetcher) {

  companion object {
    private val LOG = LoggerFactory.getLogger(ArchiveDateFetcher.javaClass)
  }

  private var previousModifiedOn = Instant.EPOCH

  fun isUpToDate(): Boolean {

    val currentModifiedOn = try {
      archiveDateFetcher.fetchModifiedOn()
    } catch (e: ArchiveDateFetchException) {
      LOG.error("Failed to fetch archive modification date.", e)
      return false
    }

    if (currentModifiedOn.isAfter(previousModifiedOn)) {
      previousModifiedOn = currentModifiedOn
      return false
    }

    return true
  }
}