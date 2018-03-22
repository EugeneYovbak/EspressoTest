package com.boost.espressotest.data.local.mapper

import com.boost.espressotest.data.local.model.ProductEntity
import com.boost.espressotest.domain.model.Product

import io.reactivex.functions.Function

class ProductContentMapper : Function<ProductEntity, Product> {

    override fun apply(productEntity: ProductEntity): Product {
        // TODO: 3/22/18 check let, apply, with kotlin extensions
        return Product(
                productEntity.id,
                productEntity.name,
                productEntity.priceInCents,
                productEntity.producerName,
                productEntity.imageUrl,
                productEntity.isFavorite)
    }
}
