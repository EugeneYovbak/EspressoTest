package com.boost.espressotest.data.repository;

import com.boost.espressotest.data.api.ApiService;
import com.boost.espressotest.data.dao.ProductDao;
import com.boost.espressotest.data.model.ApiResponse;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public class ProductRepositoryImpl implements ProductRepository {

    private final ApiService mApiService;
    private final ProductDao mProductDao;

    public ProductRepositoryImpl(ApiService apiService, ProductDao productDao) {
        mApiService = apiService;
        mProductDao = productDao;
    }

    @Override
    public Observable<List<Product>> getProductList(int page, int perPage) {
        return mApiService.loadAllProductsByQuery(page, perPage)
                .toObservable()
                .map(ApiResponse::getData);
    }

    @Override
    public Observable<Product> getProduct(long productId) {
        return mApiService.loadProductById(productId)
                .toObservable()
                .map(ApiResponse::getData);
    }

    @Override
    public Observable<Boolean> checkProductFavorite(long productId) {
        return mProductDao.getProductById(productId)
                .toObservable()
                .map(product -> true);
    }

    @Override
    public Observable<Boolean> changeProductFavoriteStatus(Product product) {

        return Observable.just(true);
    }
}
