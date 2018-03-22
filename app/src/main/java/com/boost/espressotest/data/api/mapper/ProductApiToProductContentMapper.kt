package com.boost.espressotest.data.api.mapper

import com.boost.espressotest.data.api.model.ProductApi
import com.boost.espressotest.data.local.model.ProductEntity
import io.reactivex.functions.Function

class ProductApiToProductContentMapper : Function<ProductApi, ProductEntity> {

    override fun apply(productApi: ProductApi): ProductEntity {
        // TODO: 3/22/18 check let, apply, with kotlin extensions
        return ProductEntity(
                productApi.id ?: 0,
                productApi.name ?: "",
                productApi.priceInCents ?: 0,
                productApi.producerName ?: "",
                productApi.imageUrl ?: "")
    }
}
