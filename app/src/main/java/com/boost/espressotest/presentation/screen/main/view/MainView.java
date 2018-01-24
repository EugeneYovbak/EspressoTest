package com.boost.espressotest.presentation.screen.main.view;

import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.presentation.BaseView;

import java.util.List;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public interface MainView extends BaseView {
    // TODO: 1/24/18 why you need two methods to display the list?
    void onProductsLoadSuccess(List<ProductContent> productList);

    void onProductsLoadError();

    void onListFiltered(List<ProductContent> productList);

    void navigateToDetailScreen(long productId);

    void internetConnectionError();
}
