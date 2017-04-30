package co.fastestpath.api.gtfs.models;

import co.fastestpath.api.gtfs.models.GtfsStop.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Builder.class)
public class GtfsRoute {

  private final String routeId;

  private final String agencyId;

  private final String routeShortName;

  private final String routeLongName;

  private final String routeDesc;

  private final String routeType;

  private final String routeUrl;

  private final String routeColor;

  private final String routeTextColor;

  private GtfsRoute(Builder builder) {
    routeId = builder.routeId;
    agencyId = builder.agencyId;
    routeShortName = builder.routeShortName;
    routeLongName = builder.routeLongName;
    routeDesc = builder.routeDesc;
    routeType = builder.routeType;
    routeUrl = builder.routeUrl;
    routeColor = builder.routeColor;
    routeTextColor = builder.routeTextColor;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getRouteId() {
    return routeId;
  }

  public String getAgencyId() {
    return agencyId;
  }

  public String getRouteShortName() {
    return routeShortName;
  }

  public String getRouteLongName() {
    return routeLongName;
  }

  public String getRouteDesc() {
    return routeDesc;
  }

  public String getRouteType() {
    return routeType;
  }

  public String getRouteUrl() {
    return routeUrl;
  }

  public String getRouteColor() {
    return routeColor;
  }

  public String getRouteTextColor() {
    return routeTextColor;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {
    @JsonProperty("route_id")
    private String routeId;
    @JsonProperty("agency_id")
    private String agencyId;
    @JsonProperty("route_short_name")
    private String routeShortName;
    @JsonProperty("route_long_name")
    private String routeLongName;
    @JsonProperty("route_desc")
    private String routeDesc;
    @JsonProperty("route_type")
    private String routeType;
    @JsonProperty("route_url")
    private String routeUrl;
    @JsonProperty("route_color")
    private String routeColor;
    @JsonProperty("route_test_color")
    private String routeTextColor;

    private Builder() {}

    public Builder routeId(String routeId) {
      this.routeId = routeId;
      return this;
    }

    public Builder agencyId(String agencyId) {
      this.agencyId = agencyId;
      return this;
    }

    public Builder routeShortName(String routeShortName) {
      this.routeShortName = routeShortName;
      return this;
    }

    public Builder routeLongName(String routeLongName) {
      this.routeLongName = routeLongName;
      return this;
    }

    public Builder routeDesc(String routeDesc) {
      this.routeDesc = routeDesc;
      return this;
    }

    public Builder routeType(String routeType) {
      this.routeType = routeType;
      return this;
    }

    public Builder routeUrl(String routeUrl) {
      this.routeUrl = routeUrl;
      return this;
    }

    public Builder routeColor(String routeColor) {
      this.routeColor = routeColor;
      return this;
    }

    public Builder routeTextColor(String routeTextColor) {
      this.routeTextColor = routeTextColor;
      return this;
    }

    public GtfsRoute build() {
      return new GtfsRoute(this);
    }
  }
}
