package co.fastestpath.api.gtfs;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GtfsArchive {

  public static final String ADDRESS = "http://data.trilliumtransit.com/gtfs/path-nj-us/";
  public static final String NAME = "path-nj-us.zip";

  public static final URL URL = createUrl();

  public static final CsvSchema SCHEMA = createSchema();

  private final Map<GtfsEntityType, File> files;

  public GtfsArchive(Builder builder) {
    files = ImmutableMap.copyOf(builder.files);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Map<GtfsEntityType, File> getFiles() {
    return files;
  }

  public static final class Builder {

    private Map<GtfsEntityType, File> files = new HashMap<>();

    private Builder() {}

    public Builder add(GtfsEntityType type,  File file) {
      this.files.put(type, file);
      return this;
    }

    public GtfsArchive build() {
      return new GtfsArchive(this);
    }
  }

  private static CsvSchema createSchema() {
    return CsvSchema.emptySchema()
      .withHeader()
      .withColumnSeparator(',');
  }

  private static URL createUrl() {
    String url = ADDRESS + "/" + NAME;
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Malformed URL to path zip " + url, e);
    }
  }
}
