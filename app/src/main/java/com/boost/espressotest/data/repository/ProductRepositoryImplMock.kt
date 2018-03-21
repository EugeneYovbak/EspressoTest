package com.boost.espressotest.data.repository

import android.content.Context
import com.boost.espressotest.R
import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.model.Product
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

class ProductRepositoryImplMock(private val mContext: Context) : ProductRepository {

    private val productList: List<Product>
        get() {
            val productList = ArrayList<Product>()
            for (i in 0..49) {
                val product = Product(
                        i.toLong(),
                        mContext.getString(R.string.mock_name) + i,
                        (i * 100).toLong(),
                        mContext.getString(R.string.mock_producer) + i,
                        mContext.getString(R.string.mock_image_url) + i,
                        i % 2 == 0)
                productList.add(product)
            }
            return productList
        }

    override fun getProductList(page: Int, perPage: Int): Observable<List<Product>> {
        return Observable.just(productList)
    }

    override fun getProduct(productId: Long): Observable<Product> {
        return Observable.just(productList[productId.toInt()])
    }

    override fun updateProductStatus(id: Long, isFavorite: Boolean): Completable {
        return Completable.fromAction { }
    }
}
