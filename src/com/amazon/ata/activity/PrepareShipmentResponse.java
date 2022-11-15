package com.amazon.ata.activity;

import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Item;
import com.amazon.ata.types.Packaging;

import java.util.Objects;

public class PrepareShipmentResponse {
    private Item item;
    private Packaging packaging;
    private FulfillmentCenter fulfillmentCenter;

    /**
     * Setter Constructor for PrepareShipmentResponse.
     */
    public PrepareShipmentResponse() {
        this.item              = null;
        this.packaging         = null;
        this.fulfillmentCenter = null;
    }

    /**
     * Setter Constructor for PrepareShipmentResponse.
     * @param item - item for PrepareShipmentResponse
     * @param packaging - used for packing item
     * @param fulfillmentCenter - fulfillment of PrepareShipmentResponse
     */
    public PrepareShipmentResponse(Item item, Packaging packaging, FulfillmentCenter fulfillmentCenter) {
        this.item = item;
        this.packaging = packaging;
        this.fulfillmentCenter = fulfillmentCenter;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public FulfillmentCenter getFulfillmentCenter() {
        return fulfillmentCenter;
    }

    public void setFulfillmentCenter(FulfillmentCenter fulfillmentCenter) {
        this.fulfillmentCenter = fulfillmentCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrepareShipmentResponse that = (PrepareShipmentResponse) o;
        return getItem().equals(that.getItem()) && getPackaging().equals(that.getPackaging()) &&
                getFulfillmentCenter().equals(that.getFulfillmentCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem(), getPackaging(), getFulfillmentCenter());
    }

    @Override
    public String toString() {
        return "PrepareShipmentResponse{" +
                "item=" + item +
                ", packaging=" + packaging +
                ", fulfillmentCenter=" + fulfillmentCenter +
                '}';
    }
}
