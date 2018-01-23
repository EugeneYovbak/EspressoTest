package com.boost.espressotest.data.content;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductContent {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price_in_cents")
    private long priceInCents;

    @ColumnInfo(name = "producer_name")
    private String producerName;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public ProductContent() {
    }

    @Ignore
    public ProductContent(long id, String name, long priceInCents, String producerName, String imageUrl) {
        this.id = id;
        this.name = name;
        this.priceInCents = priceInCents;
        this.producerName = producerName;
        this.imageUrl = imageUrl;
    }

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
