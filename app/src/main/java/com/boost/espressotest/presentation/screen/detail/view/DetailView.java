package com.boost.espressotest.presentation.screen.detail.view;

import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.presentation.BaseView;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface DetailView extends BaseView {
    void onProductLoadSuccess(ProductContent product);

    void onProductLoadError();

    void onProductStatusUpdateSuccess(ProductContent product);

    void onProductStatusUpdateError();
}
