package com.boost.espressotest.data.repository;

import com.boost.espressotest.data.api.ApiService;
import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.data.dao.ProductDao;
import com.boost.espressotest.data.mapper.ProductMapper;
import com.boost.espressotest.data.response.BaseResponse;
import com.boost.espressotest.domain.ProductRepository;

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
    public Observable<List<ProductContent>> getProductList(int page, int perPage) {
        return mApiService.loadAllProductsByQuery(page, perPage)
                .toObservable()
                .map(BaseResponse::getData)
                .flatMap(Observable::fromIterable)
                .map(new ProductMapper())
                .toList()
                .toObservable()
                .map(productList -> {
                    mProductDao.insertProducts(productList);
                    return productList;
                });
    }

    @Override
    public Observable<ProductContent> getProduct(long productId) {
        return mProductDao.getProduct(productId)
                .toObservable();
    }

    @Override
    public Observable<ProductContent> updateProductStatus(ProductContent product) {
        return Observable.fromCallable(() -> {
            product.setFavorite(!product.isFavorite());
            mProductDao.updateProduct(product);
            return product;
        });
    }
}
