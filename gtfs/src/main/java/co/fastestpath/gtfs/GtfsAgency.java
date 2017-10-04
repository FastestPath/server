package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsAgency.Builder.class)
public class GtfsAgency implements GtfsEntity {

  private final String id;

  private final String name;

  private final String url;

  private final String timezone;

  private final String language;

  private final String phone;

  private final String fareUrl;

  private final String email;

  private GtfsAgency(Builder builder) {
    id = builder.id;
    name = builder.name;
    url = builder.url;
    timezone = builder.timezone;
    language = builder.language;
    phone = builder.phone;
    fareUrl = builder.fareUrl;
    email = builder.email;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public String getTimezone() {
    return timezone;
  }

  public String getLanguage() {
    return language;
  }

  public String getPhone() {
    return phone;
  }

  public String getFareUrl() {
    return fareUrl;
  }

  public String getEmail() {
    return email;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("agency_id")
    private String id;

    @JsonProperty("agency_name")
    private String name;

    @JsonProperty("agency_url")
    private String url;

    @JsonProperty("agency_timezone")
    private String timezone;

    @JsonProperty("agency_lang")
    private String language;

    @JsonProperty("agency_phone")
    private String phone;

    @JsonProperty("agency_fare_url")
    private String fareUrl;

    @JsonProperty("agency_email")
    private String email;

    private Builder() {}

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder timezone(String timezone) {
      this.timezone = timezone;
      return this;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder fareUrl(String fareUrl) {
      this.fareUrl = fareUrl;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public GtfsAgency build() {
      return new GtfsAgency(this);
    }
  }
}
