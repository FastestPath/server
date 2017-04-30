package co.fastestpath.api.gtfs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Locale;

class GtfsArchiveDateFetcher {

  private static final Logger LOG = LoggerFactory.getLogger(GtfsArchiveDateFetcher.class);

  private static final DateFormat MODIFIED_ON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd mm:ss",
      Locale.ENGLISH);

  private static final String MODIFIED_ON_ELEMENT_SELECTOR = "tr > td:eq(2)";

  public static Instant fetchModifiedOn() throws GtfsArchiveDateFetchingException {
    LOG.info("Fetching GTFS archive modifiedOn date...");

    Document document;
    String archiveAddress = GtfsArchive.ADDRESS;
    try {
      document = Jsoup.connect(archiveAddress).get();
    } catch (IOException e) {
      throw new GtfsArchiveDateFetchingException("Failed to read directory at " + archiveAddress, e);
    }

    Element element = document.select(MODIFIED_ON_ELEMENT_SELECTOR).get(1);
    String modifiedOnString = element.text();
    Instant modifiedOn;
    try {
      modifiedOn = MODIFIED_ON_DATE_FORMAT.parse(modifiedOnString).toInstant();
    } catch (ParseException e) {
      throw new GtfsArchiveDateFetchingException("Unable to parse date, " +  modifiedOnString, e);
    }

    LOG.info("Schedule last updated {}.", modifiedOn);
    return modifiedOn;
  }

  private GtfsArchiveDateFetcher() {}
}
