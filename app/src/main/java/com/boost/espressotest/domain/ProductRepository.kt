package com.boost.espressotest.domain

import com.boost.espressotest.domain.model.Product

import io.reactivex.Completable
import io.reactivex.Observable

interface ProductRepository {
    fun getProductList(page: Int, perPage: Int): Observable<List<Product>>

    fun getProduct(productId: Long): Observable<Product>

    fun updateProductStatus(id: Long, isFavorite: Boolean): Completable
}
