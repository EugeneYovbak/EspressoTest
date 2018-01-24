package com.boost.espressotest.data.local.mapper;

import com.boost.espressotest.data.local.model.ProductEntity;
import com.boost.espressotest.domain.model.Product;

import io.reactivex.functions.Function;

public class ProductContentMapper implements Function<ProductEntity, Product> {

    @Override
    public Product apply(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPriceInCents(),
                productEntity.getProducerName(),
                productEntity.getImageUrl(),
                productEntity.isFavorite());
    }
}
