package com.boost.espressotest.data.repository;

import android.content.Context;

import com.boost.espressotest.R;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
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
    public Observable<List<Product>> getProductList(int page, int perPage) {
        return Observable.just(getProductList());
    }

    @Override
    public Observable<Product> getProduct(long productId) {
        return Observable.just(getProductList().get((int) productId));
    }

    @Override
    public Completable updateProductStatus(long id, boolean isFavorite) {
        return Completable.fromAction(() -> {});
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Product product = new Product(
                    i,
                    mContext.getString(R.string.mock_name) + i,
                    i * 100,
                    mContext.getString(R.string.mock_producer) + i,
                    mContext.getString(R.string.mock_image_url) + i,
                    i % 2 == 0);
            productList.add(product);
        }
        return productList;
    }
}
