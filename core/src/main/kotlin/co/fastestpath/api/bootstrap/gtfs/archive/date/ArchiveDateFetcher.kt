package co.fastestpath.api.bootstrap.gtfs.archive.date

import co.fastestpath.api.bootstrap.gtfs.archive.Archive
import co.fastestpath.api.utils.DocumentFetcher
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArchiveDateFetcher @Inject constructor(
    private val documentFetcher: DocumentFetcher
) {

  companion object {
    private val LOG = LoggerFactory.getLogger(ArchiveDateFetcher.javaClass)

    private val MODIFIED_ON_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd mm:ss")
    private val MODIFIED_ON_ELEMENT_SELECTOR = "tr > td:eq(2)"
  }

  fun fetchModifiedOn(archiveUrl: URL): Instant {
    LOG.info("Fetching GTFS archive modifiedOn date...")

    val document = try {
      documentFetcher.fetch(archiveUrl)
    } catch (e: IOException) {
      throw ArchiveDateFetchException("Failed to read directory at " + archiveUrl.toString(), e)
    }

    val element = document.select(MODIFIED_ON_ELEMENT_SELECTOR)[1]
    val modifiedOnString = element.text()

    val modifiedOn = try {
      MODIFIED_ON_DATE_FORMAT.parse(modifiedOnString).toInstant()
    } catch (e: ParseException) {
      throw ArchiveDateFetchException("Unable to parse date, " + modifiedOnString, e)
    }

    LOG.info("Schedule last updated {}.", modifiedOn)
    return modifiedOn
  }
}