package com.boost.espressotest.data.repository;

import android.content.Context;

import com.boost.espressotest.R;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;

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
    public Observable<List<Product>> getProductList(int page, int perPage) {
        return Observable.just(getProductList());
    }

    @Override
    public Observable<Product> getProduct(long productId) {
        return Observable.just(getProductList().get((int) productId));
    }

    @Override
    public Observable<Product> updateProductStatus(Product product) {
        return Observable.fromCallable(() -> {
            product.setFavorite(!product.isFavorite());
            return product;
        });
    }

    //MOCK
    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName(mContext.getString(R.string.mock_name) + i);
            product.setImageUrl(mContext.getString(R.string.mock_image_url) + i);
            product.setPriceInCents(i * 100);
            product.setProducerName(mContext.getString(R.string.mock_producer) + i);
            product.setFavorite(i % 2 == 0);
            productList.add(product);
        }
        return productList;
    }
}
