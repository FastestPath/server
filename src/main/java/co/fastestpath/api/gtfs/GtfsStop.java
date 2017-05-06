package co.fastestpath.api.gtfs;

import co.fastestpath.api.gtfs.GtfsStop.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsStop implements GtfsEntity {

  private final String name;

  private final String id;

  private final String code;

  private final String platform;

  private final String desc;

  private final Float lat;

  private final Float lon;

  private final String zoneId;

  private final String url;

  private final GtfsLocationType locationType;

  private final String parentStation;

  private final String timeZone;

  private GtfsStop(Builder builder) {
    name = builder.name;
    id = builder.id;
    code = builder.code;
    platform = builder.platform;
    desc = builder.desc;
    lat = builder.lat;
    lon = builder.lon;
    zoneId = builder.zoneId;
    url = builder.url;
    locationType = builder.locationType;
    parentStation = builder.parentStation;
    timeZone = builder.timeZone;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getPlatform() {
    return platform;
  }

  public String getDesc() {
    return desc;
  }

  public Float getLat() {
    return lat;
  }

  public Float getLon() {
    return lon;
  }

  public String getZoneId() {
    return zoneId;
  }

  public String getUrl() {
    return url;
  }

  public GtfsLocationType getLocationType() {
    return locationType;
  }

  public String getParentStation() {
    return parentStation;
  }

  public String getTimeZone() {
    return timeZone;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("stop_name")
    private String name;

    @JsonProperty("stop_id")
    private String id;

    @JsonProperty("stop_code")
    private String code;

    @JsonProperty("platform_code")
    private String platform;

    @JsonProperty("stop_desc")
    private String desc;

    @JsonProperty("stop_lat")
    private Float lat;

    @JsonProperty("stop_lon")
    private Float lon;

    @JsonProperty("zone_id")
    private String zoneId;

    @JsonProperty("stop_url")
    private String url;

    @JsonProperty("location_type")
    private GtfsLocationType locationType;

    @JsonProperty("parent_station")
    private String parentStation;

    @JsonProperty("stop_timezone")
    private String timeZone;

    public Builder() {
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder id(String val) {
      id = val;
      return this;
    }

    public Builder code(String val) {
      code = val;
      return this;
    }

    public Builder platform(String val) {
      platform = val;
      return this;
    }

    public Builder desc(String val) {
      desc = val;
      return this;
    }

    public Builder lat(Float val) {
      lat = val;
      return this;
    }

    public Builder lon(Float val) {
      lon = val;
      return this;
    }

    public Builder zoneId(String val) {
      zoneId = val;
      return this;
    }

    public Builder url(String val) {
      url = val;
      return this;
    }

    public Builder locationType(GtfsLocationType val) {
      locationType = val;
      return this;
    }

    public Builder parentStation(String val) {
      parentStation = val;
      return this;
    }

    public Builder timeZone(String val) {
      timeZone = val;
      return this;
    }

    public GtfsStop build() {
      return new GtfsStop(this);
    }
  }
}
