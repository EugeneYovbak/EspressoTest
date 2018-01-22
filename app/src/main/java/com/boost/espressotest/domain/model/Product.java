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

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
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

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
