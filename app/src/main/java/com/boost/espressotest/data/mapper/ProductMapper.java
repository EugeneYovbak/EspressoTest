package com.boost.espressotest.data.mapper;


import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.data.response.ProductResponse;

import io.reactivex.functions.Function;

public class ProductMapper implements Function<ProductResponse, ProductContent> {

    @Override
    public ProductContent apply(ProductResponse productResponse) {
        return new ProductContent(
                productResponse.getId(),
                productResponse.getName(),
                productResponse.getPriceInCents(),
                productResponse.getProducerName(),
                productResponse.getImageUrl());
    }
}
