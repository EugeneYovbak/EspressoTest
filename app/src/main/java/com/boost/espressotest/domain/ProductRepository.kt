package com.boost.espressotest.domain

import com.boost.espressotest.domain.model.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {
    fun getProductList(page: Int, perPage: Int): Single<List<Product>>

    fun getProduct(productId: Long): Single<Product>

    fun updateProductStatus(id: Long, isFavorite: Boolean): Completable
}
