package co.fastestpath.api.schedule.route;

public class Route {

  private final RouteId id;

  private final AgencyId agencyId;

  private final String shortName;

  private final String longName;

  private final String description;

  private final String type;

  private final String url;

  private final String color;

  private final String textColor;

  private Route(Builder builder) {
    id = builder.id;
    agencyId = builder.agencyId;
    shortName = builder.shortName;
    longName = builder.longName;
    description = builder.description;
    type = builder.type;
    url = builder.url;
    color = builder.color;
    textColor = builder.textColor;
  }

  public static Builder builder() {
    return new Builder();
  }

  public RouteId getId() {
    return id;
  }

  public AgencyId getAgencyId() {
    return agencyId;
  }

  public String getShortName() {
    return shortName;
  }

  public String getLongName() {
    return longName;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  public String getColor() {
    return color;
  }

  public String getTextColor() {
    return textColor;
  }

  public static final class Builder {
    private RouteId id;
    private AgencyId agencyId;
    private String shortName;
    private String longName;
    private String description;
    private String type;
    private String url;
    private String color;
    private String textColor;

    private Builder() {}

    public Builder id(RouteId id) {
      this.id = id;
      return this;
    }

    public Builder agencyId(AgencyId agencyId) {
      this.agencyId = agencyId;
      return this;
    }

    public Builder shortName(String shortName) {
      this.shortName = shortName;
      return this;
    }

    public Builder longName(String longName) {
      this.longName = longName;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder color(String color) {
      this.color = color;
      return this;
    }

    public Builder textColor(String textColor) {
      this.textColor = textColor;
      return this;
    }

    public Route build() {
      return new Route(this);
    }
  }
}
