package com.boost.espressotest.domain;

import com.boost.espressotest.data.content.ProductContent;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */
// TODO: 1/24/18 domain should be independent from data source. Create model that don't related for any source
public interface ProductRepository {
    Observable<List<ProductContent>> getProductList(int page, int perPage);

    Observable<ProductContent> getProduct(long productId);

    Observable<ProductContent> updateProductStatus(ProductContent product);
}
