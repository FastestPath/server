package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsFeedInfo.Builder.class)
public class GtfsFeedInfo implements GtfsEntity {

  private final String publisherName;

  private final String publisherUrl;

  private final String language;

  private final String startDate;

  private final String endDate;

  private final String version;

  private GtfsFeedInfo(Builder builder) {
    publisherName = builder.publisherName;
    publisherUrl = builder.publisherUrl;
    language = builder.language;
    startDate = builder.startDate;
    endDate = builder.endDate;
    version = builder.version;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("feed_publisher_name")
    private String publisherName;

    @JsonProperty("feed_publisher_url")
    private String publisherUrl;

    @JsonProperty("feed_lang")
    private String language;

    @JsonProperty("feed_start_date")
    private String startDate;

    @JsonProperty("feed_end_date")
    private String endDate;

    @JsonProperty("feed_version")
    private String version;

    private Builder() {}

    public Builder publisherName(String publisherName) {
      this.publisherName = publisherName;
      return this;
    }

    public Builder publisherUrl(String publisherUrl) {
      this.publisherUrl = publisherUrl;
      return this;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder startDate(String startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(String endDate) {
      this.endDate = endDate;
      return this;
    }

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public GtfsFeedInfo build() {
      return new GtfsFeedInfo(this);
    }
  }
}
