package com.boost.espressotest.presentation.screen.main.view;

import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.base.BaseView;

import java.util.List;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public interface MainView extends BaseView {

    void showProducts(List<Product> productList);

    void navigateToDetailScreen(long productId);

    void productsLoadError();

    void internetConnectionError();
}
