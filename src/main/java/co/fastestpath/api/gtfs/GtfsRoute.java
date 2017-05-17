package co.fastestpath.api.gtfs;

import co.fastestpath.api.gtfs.GtfsRoute.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsRoute implements GtfsEntity {

  private final String id;

  private final String agencyId;

  private final String shortName;

  private final String longName;

  private final String description;

  private final String type;

  private final String url;

  private final String color;

  private final String textColor;

  private final String sortOrder;

  private final Integer minHeadwayMinutes;

  private GtfsRoute(Builder builder) {
    id = builder.id;
    agencyId = builder.agencyId;
    shortName = builder.shortName;
    longName = builder.longName;
    description = builder.description;
    type = builder.type;
    url = builder.url;
    color = builder.color;
    textColor = builder.textColor;
    sortOrder = builder.sortOrder;
    minHeadwayMinutes = builder.minHeadwayMinutes;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public String getAgencyId() {
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

  public String getsortOrder() {
    return sortOrder;
  }

  public Integer getMinHeadwayMinutes() {
    return minHeadwayMinutes;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("route_id")
    private String id;

    @JsonProperty("agency_id")
    private String agencyId;

    @JsonProperty("route_short_name")
    private String shortName;

    @JsonProperty("route_long_name")
    private String longName;

    @JsonProperty("route_desc")
    private String description;

    @JsonProperty("route_type")
    private String type;

    @JsonProperty("route_url")
    private String url;

    @JsonProperty("route_color")
    private String color;

    @JsonProperty("route_text_color")
    private String textColor;

    @JsonProperty("route_sort_order")
    private String sortOrder;

    @JsonProperty("min_headway_minutes")
    private Integer minHeadwayMinutes;

    private Builder() {}

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder agencyId(String agencyId) {
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
    
    public Builder sortOrder(String sortOrder) {
      this.sortOrder = sortOrder;
      return this;
    }

    public Builder minHeadwayMinutes(Integer minHeadwayMinutes) {
      this.minHeadwayMinutes = minHeadwayMinutes;
      return this;
    }

    public GtfsRoute build() {
      return new GtfsRoute(this);
    }
  }
}