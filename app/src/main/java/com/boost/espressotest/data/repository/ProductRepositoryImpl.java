package com.boost.espressotest.data.repository;

import com.boost.espressotest.data.api.ApiService;
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

    public ProductRepositoryImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<List<Product>> getProductList(String query, String where, int page) {
        return mApiService.loadAllProductsByQuery(query, where, page)
                .toObservable()
                .map(ApiResponse::getData);
    }

    @Override
    public Observable<Product> getProduct(long productId) {
        return mApiService.loadProductById(productId)
                .toObservable()
                .map(ApiResponse::getData);
    }
}
