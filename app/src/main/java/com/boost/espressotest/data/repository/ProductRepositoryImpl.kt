package com.boost.espressotest.data.repository

import com.boost.espressotest.data.api.ApiService
import com.boost.espressotest.data.api.mapper.ProductApiToProductContentMapper
import com.boost.espressotest.data.local.dao.ProductDao
import com.boost.espressotest.data.local.mapper.ProductContentMapper
import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.model.Product
import io.reactivex.Completable
import io.reactivex.Observable

class ProductRepositoryImpl(private val mApiService: ApiService, private val mProductDao: ProductDao) : ProductRepository {

    // TODO: 3/22/18 why observable instead of single? are you waiting for something else?
    override fun getProductList(page: Int, perPage: Int): Observable<List<Product>> {
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
                .toObservable()
    }

    // TODO: 3/22/18 why observable instead of single? are you waiting for something else?
    override fun getProduct(productId: Long): Observable<Product> {
        return mProductDao.getProduct(productId)
                .map(ProductContentMapper())
                .toObservable()
    }

    override fun updateProductStatus(id: Long, isFavorite: Boolean): Completable {
        return Completable.fromAction { mProductDao.updateProduct(id, isFavorite) }
    }
}
