package com.boost.espressotest.data.api.mapper;


import com.boost.espressotest.data.api.model.ProductApi;
import com.boost.espressotest.data.local.model.ProductEntity;

import io.reactivex.functions.Function;

public class ProductApiToProductContentMapper implements Function<ProductApi, ProductEntity> {

    @Override
    public ProductEntity apply(ProductApi productApi) {
        return new ProductEntity(
                productApi.getId(),
                productApi.getName(),
                productApi.getPriceInCents(),
                productApi.getProducerName(),
                productApi.getImageUrl());
    }
}
