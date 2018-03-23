package com.boost.espressotest.data.api.mapper

import com.boost.espressotest.data.api.model.ProductApi
import com.boost.espressotest.data.local.model.ProductEntity
import io.reactivex.functions.Function

class ProductApiToProductContentMapper : Function<ProductApi, ProductEntity> {

    override fun apply(productApi: ProductApi): ProductEntity {
        with(productApi) {
            return ProductEntity(
                    id ?: 0,
                    name ?: "",
                    priceInCents ?: 0,
                    producerName ?: "",
                    imageUrl ?: "")
        }
    }
}
