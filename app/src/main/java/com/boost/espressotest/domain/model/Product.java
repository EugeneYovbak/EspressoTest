package com.boost.espressotest.domain.model;


public class Product {

    private long id;
    private String name;
    private long priceInCents;
    private String producerName;
    private String imageUrl;
    private boolean favorite;

    public Product(long id, String name, long priceInCents, String producerName, String imageUrl, boolean favorite) {
        this.id = id;
        this.name = name;
        this.priceInCents = priceInCents;
        this.producerName = producerName;
        this.imageUrl = imageUrl;
        this.favorite = favorite;
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
