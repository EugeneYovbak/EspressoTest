package com.boost.espressotest.presentation.screen.main.view;

import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.BaseView;

import java.util.List;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public interface MainView extends BaseView {
    void onProductsLoadSuccess(List<Product> productList);
    void onProductsLoadError();
}
