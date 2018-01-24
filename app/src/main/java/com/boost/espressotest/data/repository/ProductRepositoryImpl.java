package com.boost.espressotest.data.repository;

import com.boost.espressotest.data.api.ApiService;
import com.boost.espressotest.data.api.mapper.ProductApiToProductContentMapper;
import com.boost.espressotest.data.api.model.BaseResponse;
import com.boost.espressotest.data.local.dao.ProductDao;
import com.boost.espressotest.data.local.mapper.ProductContentMapper;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;

import java.util.List;

import io.reactivex.Completable;
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
                .map(BaseResponse::getData)
                .flatMap(Observable::fromIterable)
                .map(new ProductApiToProductContentMapper())
                .toList()
                .toObservable()
                .map(productList -> {
                    mProductDao.insertProducts(productList);
                    return productList;
                })
                .flatMap(Observable::fromIterable)
                .map(new ProductContentMapper())
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Product> getProduct(long productId) {
        return mProductDao.getProduct(productId)
                .map(new ProductContentMapper())
                .toObservable();
    }

    @Override
    public Completable updateProductStatus(long id, boolean isFavorite) {
        return Completable.fromAction(() -> mProductDao.updateProduct(id, isFavorite));
    }
}
