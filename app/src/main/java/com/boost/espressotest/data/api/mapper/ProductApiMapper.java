package com.boost.espressotest.data.api.mapper;


import com.boost.espressotest.data.api.model.ProductApi;
import com.boost.espressotest.data.local.model.ProductContent;

import io.reactivex.functions.Function;

// TODO: 1/24/18 ProductApiToProductContentMapper sounds better
public class ProductApiMapper implements Function<ProductApi, ProductContent> {

    @Override
    public ProductContent apply(ProductApi productApi) {
        return new ProductContent(
                productApi.getId(),
                productApi.getName(),
                productApi.getPriceInCents(),
                productApi.getProducerName(),
                productApi.getImageUrl());
    }
}
