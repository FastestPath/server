package co.fastestpath.api.schedule;

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

import static co.fastestpath.api.schedule.ScheduleFetcher.SCHEDULE_ADDRESS;

class ScheduleDateFetcher {

  private static final Logger LOG = LoggerFactory.getLogger(ScheduleDateFetcher.class);

  private static final DateFormat MODIFIED_ON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd mm:ss",
      Locale.ENGLISH);

  private static final String MODIFIED_ON_ELEMENT_SELECTOR = "tr > td:eq(2)";

  public static Instant fetchModifiedOn() throws ScheduleFetcherException {
    LOG.info("Fetching schedule modifiedOn date...");

    Document document;
    try {
      document = Jsoup.connect(SCHEDULE_ADDRESS).get();
    } catch (IOException e) {
      throw new ScheduleFetcherException("Failed to read PATH directory at " + SCHEDULE_ADDRESS, e);
    }

    Element element = document.select(MODIFIED_ON_ELEMENT_SELECTOR).get(1);
    String modifiedOnString = element.text();
    Instant modifiedOn;
    try {
      modifiedOn = MODIFIED_ON_DATE_FORMAT.parse(modifiedOnString).toInstant();
    } catch (ParseException e) {
      throw new ScheduleFetcherException("Unable to parse date, " +  modifiedOnString, e);
    }

    LOG.info("Schedule last updated {}.", modifiedOn);

    return modifiedOn;
  }

  private ScheduleDateFetcher() {}
}
