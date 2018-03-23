package com.boost.espressotest.data.local.mapper

import com.boost.espressotest.data.local.model.ProductEntity
import com.boost.espressotest.domain.model.Product

import io.reactivex.functions.Function

class ProductContentMapper : Function<ProductEntity, Product> {

    override fun apply(productEntity: ProductEntity): Product {
        with(productEntity) {
            return Product(id, name, priceInCents, producerName, imageUrl, isFavorite)
        }
    }
}
