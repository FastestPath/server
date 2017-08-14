package co.fastestpath.api.schedule.agency;

import java.time.ZoneId;

public class Agency {

  private final AgencyId id;

  private final String name;

  private final String url;

  private final ZoneId timeZone;

  private final String language;

  private final String phone;

  private final String fareUrl;

  private final String email;

  private Agency(Builder builder) {
    id = builder.id;
    name = builder.name;
    url = builder.url;
    timeZone = builder.timeZone;
    language = builder.language;
    phone = builder.phone;
    fareUrl = builder.fareUrl;
    email = builder.email;
  }

  public static Builder builder() {
    return new Builder();
  }

  public AgencyId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public ZoneId getTimeZone() {
    return timeZone;
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

  public static final class Builder {
    private AgencyId id;
    private String name;
    private String url;
    private ZoneId timeZone;
    private String language;
    private String phone;
    private String fareUrl;
    private String email;

    private Builder() {
    }

    public Builder id(AgencyId id) {
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

    public Builder timeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
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

    public Agency build() {
      return new Agency(this);
    }
  }
}
