package com.boost.espressotest.data.api.mapper


import com.boost.espressotest.data.api.model.ProductApi
import com.boost.espressotest.data.local.model.ProductEntity

import io.reactivex.functions.Function

class ProductApiToProductContentMapper : Function<ProductApi, ProductEntity> {

    override fun apply(productApi: ProductApi): ProductEntity {
        return ProductEntity(
                productApi.id,
                productApi.name,
                productApi.priceInCents,
                productApi.producerName,
                productApi.imageUrl)
    }
}
