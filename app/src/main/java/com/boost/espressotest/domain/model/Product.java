package com.boost.espressotest.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

@Entity(tableName = "favorite_products")
public class Product {

    public static final String PRIMARY_KEY = "id";

    @PrimaryKey
    @SerializedName(PRIMARY_KEY)
    @ColumnInfo(name = PRIMARY_KEY)
    private long id;
    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("price_in_cents")
    @ColumnInfo(name = "price_in_cents")
    private long priceInCents;
    @SerializedName("producer_name")
    @ColumnInfo(name = "producer_name")
    private String producerName;
    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
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

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
