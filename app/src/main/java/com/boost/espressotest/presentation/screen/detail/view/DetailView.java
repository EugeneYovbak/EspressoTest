package com.boost.espressotest.presentation.screen.detail.view;

import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.BaseView;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface DetailView extends BaseView {
    void productLoadSuccess(Product product);

    void productLoadError();

    void productStatusUpdateSuccess(boolean isFavorite);

    void productStatusUpdateError();
}
