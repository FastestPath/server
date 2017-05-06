package co.fastestpath.api.schedule;

public class Stop {

  private final StopId id;

  private final StopId parentId;

  private final String code;

  private final String name;

  private final String description;

  private final Coordinates coordinates;

  private final String zoneId;

  private final String url;

  private final LocationType locationType;

  private Stop(Builder builder) {
    id = builder.id;
    parentId = builder.parentId;
    code = builder.code;
    name = builder.name;
    description = builder.description;
    coordinates = builder.coordinates;
    zoneId = builder.zoneId;
    url = builder.url;
    locationType = builder.locationType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public StopId getId() {
    return id;
  }

  public StopId getParentId() {
    return parentId;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public String getZoneId() {
    return zoneId;
  }

  public String getUrl() {
    return url;
  }

  public LocationType getLocationType() {
    return locationType;
  }

  public static final class Builder {
    private StopId id;
    private StopId parentId;
    private String code;
    private String name;
    private String description;
    private Coordinates coordinates;
    private String zoneId;
    private String url;
    private LocationType locationType;

    private Builder() {
    }

    public Builder id(StopId id) {
      this.id = id;
      return this;
    }

    public Builder parentId(StopId parentId) {
      this.parentId = parentId;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder coordinates(Coordinates coordinates) {
      this.coordinates = coordinates;
      return this;
    }

    public Builder zoneId(String zoneId) {
      this.zoneId = zoneId;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder locationType(LocationType locationType) {
      this.locationType = locationType;
      return this;
    }

    public Stop build() {
      return new Stop(this);
    }
  }
}
