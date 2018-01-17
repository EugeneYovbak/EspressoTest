package com.boost.espressotest.screen.main.view;

import com.boost.espressotest.base.BaseView;
import com.boost.espressotest.model.Product;

import java.util.List;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public interface MainView extends BaseView {
    void onProductsLoadSuccess(List<Product> productList);
    void onProductsLoadError();
}
