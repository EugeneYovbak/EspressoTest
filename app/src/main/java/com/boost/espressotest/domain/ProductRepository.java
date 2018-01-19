package com.boost.espressotest.domain;

import com.boost.espressotest.data.model.ApiResponse;
import com.boost.espressotest.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface ProductRepository {
    Observable<ApiResponse<List<Product>>> getProductList(String query, String where, int page);

    Observable<ApiResponse<Product>> getProduct(long productId);
}
