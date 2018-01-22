package com.boost.espressotest.domain;

import com.boost.espressotest.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface ProductRepository {
    Observable<List<Product>> getProductList(int page, int perPage);

    Observable<Product> getProduct(long productId);

    Observable<Boolean> checkProductFavorite(long productId);

    Observable<Boolean> changeProductFavoriteStatus(Product product);
}
