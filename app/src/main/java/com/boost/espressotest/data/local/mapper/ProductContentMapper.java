package com.boost.espressotest.data.local.mapper;

import com.boost.espressotest.data.local.model.ProductContent;
import com.boost.espressotest.domain.model.Product;

import io.reactivex.functions.Function;

public class ProductContentMapper implements Function<ProductContent, Product> {

    @Override
    public Product apply(ProductContent productContent) {
        return new Product(
                productContent.getId(),
                productContent.getName(),
                productContent.getPriceInCents(),
                productContent.getProducerName(),
                productContent.getImageUrl(),
                productContent.isFavorite());
    }
}
