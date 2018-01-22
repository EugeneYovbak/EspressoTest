package com.boost.espressotest.presentation.screen.detail.view;

import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.BaseView;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface DetailView extends BaseView {
    void onProductLoadSuccess(Product product);

    void onProductLoadError();

    void onProductFavoriteChecked(Boolean isFavorite);

    void onProductFavoriteStatusChanged(Boolean isFavorite);

    void internetConnectionError();
}
