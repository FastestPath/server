package co.fastestpath.gtfs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GtfsFareAttributes.Builder.class)
public class GtfsFareAttributes implements GtfsEntity {

  private final String fareId;

  private final String price;

  private final String currencyType;

  private final GtfsPaymentMethodType paymentMethod;

  private final GtfsFareTransferType transfers;

  private final String transferDuration;

  private GtfsFareAttributes(Builder builder) {
    fareId = builder.fareId;
    price = builder.price;
    currencyType = builder.currencyType;
    paymentMethod = builder.paymentMethod;
    transfers = builder.transfers;
    transferDuration = builder.transferDuration;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    @JsonProperty("fare_id")
    private String fareId;

    @JsonProperty("price")
    private String price;

    @JsonProperty("currency_type")
    private String currencyType;

    @JsonProperty("payment_method")
    private GtfsPaymentMethodType paymentMethod;

    @JsonProperty("transfers")
    private GtfsFareTransferType transfers;

    @JsonProperty("transfer_duration")
    private String transferDuration;

    private Builder() {}

    public Builder fareId(String fareId) {
      this.fareId = fareId;
      return this;
    }

    public Builder price(String price) {
      this.price = price;
      return this;
    }

    public Builder currencyType(String currencyType) {
      this.currencyType = currencyType;
      return this;
    }

    public Builder paymentMethod(GtfsPaymentMethodType paymentMethod) {
      this.paymentMethod = paymentMethod;
      return this;
    }

    public Builder transfers(GtfsFareTransferType transfers) {
      this.transfers = transfers;
      return this;
    }

    public Builder transferDuration(String transferDuration) {
      this.transferDuration = transferDuration;
      return this;
    }

    public GtfsFareAttributes build() {
      return new GtfsFareAttributes(this);
    }
  }
}
