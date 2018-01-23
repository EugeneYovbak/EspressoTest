package com.boost.espressotest.data.repository;

import android.content.Context;

import com.boost.espressotest.R;
import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.domain.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author PerSpiKyliaTor on 23.01.18.
 */

public class ProductRepositoryImplMock implements ProductRepository {

    private final Context mContext;

    public ProductRepositoryImplMock(Context context) {
        mContext = context;
    }

    @Override
    public Observable<List<ProductContent>> getProductList(int page, int perPage) {
        return Observable.just(getProductList());
    }

    @Override
    public Observable<ProductContent> getProduct(long productId) {
        return Observable.just(getProductList().get((int) productId));
    }

    @Override
    public Observable<ProductContent> updateProductStatus(ProductContent product) {
        return Observable.fromCallable(() -> {
            product.setFavorite(!product.isFavorite());
            return product;
        });
    }

    //MOCK
    private List<ProductContent> getProductList() {
        List<ProductContent> productList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ProductContent product = new ProductContent(
                    i,
                    mContext.getString(R.string.mock_name) + i,
                    i * 100,
                    mContext.getString(R.string.mock_image_url) + i,
                    mContext.getString(R.string.mock_producer) + i);
            product.setFavorite(i % 2 == 0);
            productList.add(product);
        }
        return productList;
    }
}
