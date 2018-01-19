package com.boost.espressotest.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public class Product {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("price_in_cents")
    private long priceInCents;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
