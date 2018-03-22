package com.boost.espressotest.data.repository

import com.boost.espressotest.data.api.ApiService
import com.boost.espressotest.data.api.mapper.ProductApiToProductContentMapper
import com.boost.espressotest.data.local.dao.ProductDao
import com.boost.espressotest.data.local.mapper.ProductContentMapper
import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.model.Product
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class ProductRepositoryImpl(private val mApiService: ApiService, private val mProductDao: ProductDao) : ProductRepository {

    override fun getProductList(page: Int, perPage: Int): Single<List<Product>> {
        return mApiService.loadAllProductsByQuery(page, perPage)
                .toObservable()
                .flatMap { Observable.fromIterable(it.data) }
                .map(ProductApiToProductContentMapper())
                .toList()
                .toObservable()
                .map { productList ->
                    mProductDao.insertProducts(productList)
                    productList
                }
                .flatMap { Observable.fromIterable(it) }
                .map(ProductContentMapper())
                .toList()
    }

    override fun getProduct(productId: Long): Single<Product> {
        return mProductDao.getProduct(productId)
                .map(ProductContentMapper())
    }

    override fun updateProductStatus(id: Long, isFavorite: Boolean): Completable {
        return Completable.fromAction { mProductDao.updateProduct(id, isFavorite) }
    }
}
